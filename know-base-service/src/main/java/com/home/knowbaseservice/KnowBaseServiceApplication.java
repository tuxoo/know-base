package com.home.knowbaseservice;


import com.home.knowbaseservice.properties.ApplicationProperty;
import com.home.knowbaseservice.properties.LiquibaseProperty;
import com.home.knowbaseservice.properties.RedisProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperty.class, LiquibaseProperty.class, RedisProperty.class})
public class KnowBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowBaseServiceApplication.class, args);
    }
}
