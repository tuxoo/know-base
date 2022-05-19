package com.home.knowbase.security;

import com.home.knowbase.dto.AuthDTO;
import com.home.knowbase.dto.CredentialDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    public AuthDTO authenticate(CredentialDTO credentialDTO) {
        return AuthDTO.builder()
                .token(UUID.randomUUID())
                .build();
    }

//    public Authentication getAuthentication(String token) {
//
//        return new UsernamePasswordAuthenticationToken();
//    }
}
