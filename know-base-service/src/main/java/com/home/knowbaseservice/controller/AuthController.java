package com.home.knowbaseservice.controller;

import com.home.knowbaseservice.dto.CredentialDTO;
import com.home.knowbaseservice.dto.UserTokenDTO;
import com.home.knowbaseservice.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public UserTokenDTO authenticate(@RequestBody CredentialDTO credentialDTO) {
        return authService.authenticate(credentialDTO);
    }
}
