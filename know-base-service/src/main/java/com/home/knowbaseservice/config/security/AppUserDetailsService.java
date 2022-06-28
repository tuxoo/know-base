package com.home.knowbaseservice.config.security;

import com.home.knowbaseservice.model.dto.UserDTO;
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
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public AppUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userService.getByEmail(email);
        return AppUserDetails.toKbaseUserDetails(user);
    }

    public AppUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        UserDTO user = userService.getById(id);
        return AppUserDetails.toKbaseUserDetails(user);
    }
}
