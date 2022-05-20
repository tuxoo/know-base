package com.home.knowbase.dto;

import java.time.Instant;
import java.util.UUID;

public record UserTokenDTO(UUID token, Instant expiresAt) {
}
