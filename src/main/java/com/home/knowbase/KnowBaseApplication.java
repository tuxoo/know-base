package com.home.knowbase;

import com.home.knowbase.properties.ApplicationProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperty.class)
public class KnowBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowBaseApplication.class, args);
    }
}
