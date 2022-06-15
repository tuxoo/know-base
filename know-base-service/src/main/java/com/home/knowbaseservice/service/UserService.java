package com.home.knowbaseservice.service;

import com.home.knowbaseservice.cache.UserDTOCache;
import com.home.knowbaseservice.config.security.JwtProvider;
import com.home.knowbaseservice.config.security.KbaseUserDetails;
import com.home.knowbaseservice.model.dto.SignInDTO;
import com.home.knowbaseservice.model.dto.SignUpDTO;
import com.home.knowbaseservice.model.dto.TokenDTO;
import com.home.knowbaseservice.model.entity.User;
import com.home.knowbaseservice.model.entity.UserDTO;
import com.home.knowbaseservice.model.enums.Role;
import com.home.knowbaseservice.model.exception.InvalidCredentialException;
import com.home.knowbaseservice.model.mapper.UserMapper;
import com.home.knowbaseservice.repository.UserRepository;
import com.home.knowbaseservice.util.HashUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserDTOCache userCache;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(SignUpDTO signUpDTO) {
        String passwordHash = HashUtils.HashSHA1(signUpDTO.password());

        User user = new User();
        user.setName(signUpDTO.name());
        user.setLoginEmail(signUpDTO.email());
        user.setPasswordHash(passwordHash);
        user.setRegisteredAt(Instant.now());
        user.setVisitedAt(Instant.now());
        user.setRole(Role.USER);
        user.setIsEnabled(false);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KbaseUserDetails details = (KbaseUserDetails) authentication.getPrincipal();
        return getByIdOrThrow(details.getId());
    }

    @Transactional(readOnly = true)
    public TokenDTO signIn(SignInDTO signInDTO) {
        String passwordHash = HashUtils.HashSHA1(signInDTO.password());

        UserDTO user = getByCredentials(signInDTO.email(), passwordHash)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new InvalidCredentialException("User not found by credentials"));

        userCache.save(user);

        String token = jwtProvider.generateToken(user.getId().toString());
        return new TokenDTO(token);
    }

    @Transactional(readOnly = true)
    public UserDTO getByLoginEmailOrThrow(String email) {
        return userRepository.findActiveUserByLoginEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found by email"));
    }

    @Transactional(readOnly = true)
    public UserDTO getByIdOrThrow(UUID id) {
        Optional<UserDTO> user = userCache.findById(id.toString());
        return user.orElseGet(() -> userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found by id")));
    }

    private Optional<User> getByCredentials(String email, String password) {
        return userRepository.findByCredentials(email, password);
    }
}
