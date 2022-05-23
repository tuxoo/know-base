package com.home.knowbaseservice.security;


import com.home.knowbaseservice.dto.CredentialDTO;
import com.home.knowbaseservice.dto.UserDTO;
import com.home.knowbaseservice.dto.UserTokenDTO;
import com.home.knowbaseservice.enums.Role;
import com.home.knowbaseservice.service.UserService;
import com.home.knowbaseservice.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserTokenService userTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserTokenDTO authenticate(CredentialDTO credential) {

        UserDTO user = userService.getUserByLogin(credential.login())
                .orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(credential.password(), user.password())) {
            throw new RuntimeException();
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
