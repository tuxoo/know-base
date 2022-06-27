package com.home.knowbaseservice.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.home.knowbaseservice.config.property.CacheProperty;
import com.home.knowbaseservice.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {

//    @Bean
//    public Caffeine caffeineConfig() {
//        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
//    }
//
//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine) {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.setCaffeine(caffeine);
//        return caffeineCacheManager;
//    }

    private final CacheProperty cacheProperty;

    @Bean
    public Cache<UUID, UserDTO> cache() {
        return Caffeine.newBuilder()
                .maximumSize(cacheProperty.userMaximumSize())
                .expireAfterAccess(cacheProperty.userExpiredTimeHours(), TimeUnit.HOURS)
                .build();
    }
}
