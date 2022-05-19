package com.home.knowbase.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthDTO(UUID token) {
}
