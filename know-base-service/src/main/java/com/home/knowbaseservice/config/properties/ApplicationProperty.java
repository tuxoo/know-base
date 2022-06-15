package com.home.knowbaseservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "app")
public record ApplicationProperty(String url, String apiPath, String jwtSigningKey) {
}
