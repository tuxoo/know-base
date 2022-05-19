package com.home.knowbase.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.liquibase")
public record LiquibaseProperty(String defaultSchema, String changeLog) {
}
