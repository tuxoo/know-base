package com.home.knowbaseservice.model.dto;

import com.home.knowbaseservice.model.enums.Role;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String loginEmail,
        Instant registeredAt,
        Instant visitedAt,
        Role role,
        Boolean isEnabled
) {
}
