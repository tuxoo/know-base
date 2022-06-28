package com.home.knowbaseservice.model.error;

import java.time.Instant;

public record ErrorResponse(
        String message,
        Instant errorTime
) {
}
