package com.home.knowbaseservice.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.home.knowbaseservice.config.security.JwtProvider;
import com.home.knowbaseservice.config.security.KbaseUserDetails;
import com.home.knowbaseservice.model.dto.*;
import com.home.knowbaseservice.model.entity.User;
import com.home.knowbaseservice.model.enums.Role;
import com.home.knowbaseservice.model.exception.IllegalCheckCodeException;
import com.home.knowbaseservice.model.exception.InvalidCredentialException;
import com.home.knowbaseservice.model.exception.UserAlreadyActiveException;
import com.home.knowbaseservice.model.exception.UserNotFoundException;
import com.home.knowbaseservice.model.mapper.UserMapper;
import com.home.knowbaseservice.repository.UserRepository;
import com.home.knowbaseservice.util.HashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Cache<UUID, UserDTO> userCache;
    private final JwtProvider jwtProvider;
    private final HashService hashService;

    public void signUp(SignUpDTO signUpDTO) {
        String passwordHash = hashService.hashSHA1(signUpDTO.password());

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

    public void verifyUser(VerifyDTO verifyDTO) {

        Optional<User> optUser = userRepository.findByEmail(verifyDTO.email(), false);

        if (optUser.isEmpty()) {
            throw new UserNotFoundException("unknown user");
        }

        User user = optUser.get();

        if (Boolean.TRUE.equals(user.getIsEnabled())) {
            throw new UserAlreadyActiveException("user already active");
        }

        if (!verifyDTO.checkCode().equals(hashService.hashSHA1(user.getName()))) {
            throw new IllegalCheckCodeException("illegal check code");
        }

        user.setIsEnabled(true);
        userRepository.save(user);
    }

    public TokenDTO signIn(SignInDTO signInDTO) {
        String passwordHash = hashService.hashSHA1(signInDTO.password());

        UserDTO user = userRepository.findByCredentials(signInDTO.email(), passwordHash)
                .map(userMapper::toDTO)
                .orElseThrow(() -> {
                    log.error(String.format("user not found by credentials [%s, %s]", signInDTO.email(), signInDTO.password()));
                    throw new InvalidCredentialException("User not found by credentials");
                });

        userCache.put(user.id(), user);
        String token = jwtProvider.generateToken(user.id().toString());
        log.info(String.format("user %s has signed in", user.name()));
        return new TokenDTO(token);
    }

    public UserDTO getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KbaseUserDetails details = (KbaseUserDetails) authentication.getPrincipal();
        return getById(details.getId());
    }

    public UserDTO getByEmail(String email) {
        return userRepository.findByEmail(email, true)
                .map(userMapper::toDTO)
                .orElseThrow(() -> {
                    log.error(String.format("user not found by email [%s]", email));
                    throw new UserNotFoundException("User not found by id");
                });
    }

    public UserDTO getById(UUID id) {
        return userCache.get(id, key ->
                userRepository.findById(id)
                        .map(userMapper::toDTO)
                        .orElseThrow(() -> {
                            log.error(String.format("user not found by id [%s]", id));
                            throw new UserNotFoundException("User not found by id");
                        })
        );
    }
}
