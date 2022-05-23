package com.home.knowbaseservice.dto;

import javax.validation.constraints.NotBlank;

public record CredentialDTO(@NotBlank String login, @NotBlank String password) {
}
