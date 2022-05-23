package com.home.knowbaseservice.security;


import com.home.knowbaseservice.dto.CredentialDTO;
import com.home.knowbaseservice.dto.UserDTO;
import com.home.knowbaseservice.dto.UserTokenDTO;
import com.home.knowbaseservice.enums.Role;
import com.home.knowbaseservice.exception.InvalidCredentialException;
import com.home.knowbaseservice.service.UserService;
import com.home.knowbaseservice.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserTokenService userTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserTokenDTO authenticate(CredentialDTO credential) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CredentialDTO>> violations = validator.validate(credential);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream().map(text -> String.format("%s %s", text.getPropertyPath(), text.getMessage()))
                    .collect(Collectors.joining(", ")).strip();
            throw new InvalidCredentialException(errorMessage);
        }

        UserDTO user = userService.getUserByLogin(credential.login())
                .orElseThrow(() -> new InvalidCredentialException("invalid login"));

        if (!passwordEncoder.matches(credential.password(), user.password())) {
            throw new InvalidCredentialException("invalid password");
        }

        return userTokenService.getTokenByUserId(user.id())
                .orElseGet(() -> userTokenService.putNewToken(user.id()));
    }

    public Authentication getAuthentication(UUID token) {
        Optional<UserDTO> user = userService.getUserByToken(token);

        if (user.isEmpty()) {
            return null;
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(Role.USER.name()));
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }
}
