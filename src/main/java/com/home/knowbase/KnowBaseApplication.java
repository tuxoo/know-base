package com.home.knowbase;

import com.home.knowbase.properties.ApplicationProperty;
import com.home.knowbase.properties.LiquibaseProperty;
import com.home.knowbase.properties.RedisProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperty.class, LiquibaseProperty.class, RedisProperty.class})
public class KnowBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowBaseApplication.class, args);
    }
}
