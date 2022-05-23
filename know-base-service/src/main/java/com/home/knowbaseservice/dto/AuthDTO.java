package com.home.knowbaseservice.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthDTO(UUID token) {
}
