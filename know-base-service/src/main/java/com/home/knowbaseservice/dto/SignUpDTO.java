package com.home.knowbaseservice.dto;

import javax.validation.constraints.NotBlank;

public record SignUpDTO(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
