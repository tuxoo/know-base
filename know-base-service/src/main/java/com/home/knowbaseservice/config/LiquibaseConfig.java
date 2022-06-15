package com.home.knowbaseservice.config;

import com.home.knowbaseservice.config.properties.LiquibaseProperty;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(LiquibaseProperty properties, DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(properties.changeLog());
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setDefaultSchema(properties.defaultSchema());
        return springLiquibase;
    }
}
