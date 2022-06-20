package com.home.knowbaseservice;


import com.home.knowbaseservice.config.property.ApplicationProperty;
import com.home.knowbaseservice.config.property.LiquibaseProperty;
import com.home.knowbaseservice.config.property.RedisProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

//@EnableCaching
@SpringBootApplication
@EnableRedisRepositories
@EnableConfigurationProperties({ApplicationProperty.class, LiquibaseProperty.class, RedisProperty.class})
public class KnowBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowBaseServiceApplication.class, args);
    }
}
