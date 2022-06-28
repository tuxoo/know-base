package com.home.knowbaseservice.model.error;

public record ErrorResponse(
        String message,
        String errorTime
) {
}
