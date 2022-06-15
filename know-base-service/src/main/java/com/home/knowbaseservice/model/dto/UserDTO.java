package com.home.knowbaseservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;

public record UserDTO(
        String name,
        @JsonProperty("email") String loginEmail,
        Instant registeredAt,
        Instant visitedAt
) implements Serializable {
}
