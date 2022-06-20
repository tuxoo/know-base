package com.home.knowbaseservice.config.security;

import com.home.knowbaseservice.config.property.ApplicationProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.home.knowbaseservice.model.enums.Auth.JWT;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final String HEADER_TYPE = "typ";

    private final ApplicationProperty property;

    public String generateToken(String login) {
        Date expiredDate = Date.from(LocalDateTime.now().plusHours(property.tokenTTL()).toLocalDate()
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setExpiration(expiredDate)
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS256, property.jwtSigningKey())
                .setHeaderParam(HEADER_TYPE, JWT.getMeaning())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(property.jwtSigningKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn(String.format("[%s] token is invalid", token));
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(property.jwtSigningKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
