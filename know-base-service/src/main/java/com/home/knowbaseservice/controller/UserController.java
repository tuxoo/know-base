package com.home.knowbaseservice.controller;

import com.home.knowbaseservice.model.dto.*;
import com.home.knowbaseservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Sign up user", description = "This method signs up new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content(mediaType = "application/json"))
            })
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpDTO signUpDTO) {
        userService.signUp(signUpDTO);
    }

    @Operation(summary = "Verify user", description = "This method verifies new user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content(mediaType = "application/json"))
            })
    @PostMapping("/verify")
    public void verifyUser(@RequestBody VerifyDTO verifyDTO) {
        userService.verifyUser(verifyDTO);
    }

    @Operation(summary = "Sign in user", description = "This method signs in new user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content(mediaType = "application/json"))
            })
    @PostMapping("/sign-in")
    public TokenDTO signIn(@RequestBody SignInDTO signInDTO) {
        return userService.signIn(signInDTO);
    }

    @Operation(summary = "Get user profile", description = "This method gets user profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content(mediaType = "application/json"))
            }, security = {@SecurityRequirement(name = "Bearer")})
    @GetMapping("/profile")
    public UserDTO getUserProfile() {
        return userService.getUserProfile();
    }

    @Operation(summary = "Get user by email", description = "This method gets user by email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal service error", content = @Content(mediaType = "application/json"))
            }, security = {@SecurityRequirement(name = "Bearer")})
    @GetMapping("/{email}")
    public UserDTO getUserByEmail(@PathVariable @NotBlank String email) {
        return userService.getByEmail(email);
    }
}

