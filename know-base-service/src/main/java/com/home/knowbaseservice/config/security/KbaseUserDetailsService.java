package com.home.knowbaseservice.config.security;

import com.home.knowbaseservice.model.entity.User;
import com.home.knowbaseservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KbaseUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public KbaseUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByLoginEmailOrThrow(email);
        return KbaseUserDetails.toKbaseUserDetails(user);
    }

    public KbaseUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        User user = userService.getByIdOrThrow(id);
        return KbaseUserDetails.toKbaseUserDetails(user);
    }
}
