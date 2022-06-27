package com.home.knowbaseservice;


import com.home.knowbaseservice.config.property.ApplicationProperty;
import com.home.knowbaseservice.config.property.CacheProperty;
import com.home.knowbaseservice.config.property.LiquibaseProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperty.class, LiquibaseProperty.class, CacheProperty.class})
public class KnowBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowBaseServiceApplication.class, args);
    }
}
