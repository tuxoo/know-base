package com.home.knowbaseservice.config.security;

import com.home.knowbaseservice.config.properties.ApplicationProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final ApplicationProperty applicationProperty;

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setExpiration(date)
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS256, applicationProperty.jwtSigningKey())
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(applicationProperty.jwtSigningKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.info("invalid token");
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(applicationProperty.jwtSigningKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
