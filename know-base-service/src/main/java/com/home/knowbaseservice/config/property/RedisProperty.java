package com.home.knowbaseservice.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.redis")
public record RedisProperty(Integer database, String host, Integer port, String password) {
}
