package com.home.knowbaseservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static com.home.knowbaseservice.enums.Auth.AUTHORIZATION;
import static com.home.knowbaseservice.enums.Auth.BEARER;
import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<UUID> token = getToken((HttpServletRequest) servletRequest);

        if (token.isEmpty()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Authentication authentication = authService.getAuthentication(token.get());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<UUID> getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION.getMeaning());
        String authorization = (hasText(authHeader) && authHeader.startsWith(BEARER.getMeaning()))
                ? authHeader.substring(BEARER.getLength()).strip()
                : "";

        UUID token = null;
        try {
            token = UUID.fromString(authorization);
        } catch (IllegalArgumentException ignored) {

        }

        return Optional.ofNullable(token);
    }
}
