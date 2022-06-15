package com.home.knowbaseservice.controller;

import com.home.knowbaseservice.dto.SignInDTO;
import com.home.knowbaseservice.dto.SignUpDTO;
import com.home.knowbaseservice.dto.TokenDTO;
import com.home.knowbaseservice.dto.UserDTO;
import com.home.knowbaseservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpDTO signUpDTO) {
        userService.signUp(signUpDTO);
    }

    @PostMapping("/sign-in")
    public TokenDTO signIn(@RequestBody SignInDTO signInDTO) {
        return userService.signIn(signInDTO);
    }

    @GetMapping("/profile")
    public UserDTO getUserProfile() {
        return userService.getUserProfile();
    }
}

