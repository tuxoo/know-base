package com.home.knowbaseservice.config.security;

import com.home.knowbaseservice.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class KbaseUserDetails implements UserDetails {

    private UUID id;
    private String login;
    private String password;
    private Boolean isEnabled;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static KbaseUserDetails toKbaseUserDetails(User user) {
        KbaseUserDetails userDetails = new KbaseUserDetails();
        userDetails.id = user.getId();
        userDetails.login = user.getLoginEmail();
        userDetails.password = user.getPasswordHash();
        userDetails.isEnabled = user.getIsEnabled();
        userDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
