package com.home.knowbaseservice.service;

import com.home.knowbaseservice.dto.SignInDTO;
import com.home.knowbaseservice.dto.SignUpDTO;
import com.home.knowbaseservice.dto.TokenDTO;
import com.home.knowbaseservice.dto.UserDTO;
import com.home.knowbaseservice.entity.User;
import com.home.knowbaseservice.enums.Role;
import com.home.knowbaseservice.exception.InvalidCredentialException;
import com.home.knowbaseservice.mapper.UserMapper;
import com.home.knowbaseservice.repository.UserRepository;
import com.home.knowbaseservice.security.JwtProvider;
import com.home.knowbaseservice.security.KbaseUserDetails;
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

    public UserDTO getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KbaseUserDetails details = (KbaseUserDetails) authentication.getPrincipal();
        User user = userRepository.findById(details.getId())
                .orElseThrow(() -> new RuntimeException("Incorrect user in system"));
        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public TokenDTO signIn(SignInDTO signInDTO) {
        String passwordHash = HashUtils.HashSHA1(signInDTO.password());

        User user = getByCredentials(signInDTO.email(), passwordHash)
                .orElseThrow(() -> new InvalidCredentialException("User not found by credentials"));

        return TokenDTO.builder()
                .token(jwtProvider.generateToken(user.getId().toString()))
                .build();
    }

    @Transactional(readOnly = true)
    public User getByLoginEmailOrThrow(String email) {
        return userRepository.findActiveUserByLoginEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found by email"));
    }

    @Transactional(readOnly = true)
    public User getByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id"));
    }

    private Optional<User> getByCredentials(String email, String password) {
        return userRepository.findByCredentials(email, password);
    }
}
