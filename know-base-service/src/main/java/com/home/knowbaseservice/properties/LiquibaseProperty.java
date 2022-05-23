package com.home.knowbaseservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.liquibase")
public record LiquibaseProperty(String defaultSchema, String changeLog) {
}
