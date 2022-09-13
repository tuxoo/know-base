package com.home.knowbaseservice.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "application")
public record ApplicationProperty(
        String url,
        String apiPath,
        String jwtSigningKey,
        Integer tokenTTL
) {
}
