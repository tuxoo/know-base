package com.home.knowbase.config;

import com.home.knowbase.properties.RedisProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperty redisProperty;

//    @Bean
//    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperty.host(), redisProperty.port());
//        redisStandaloneConfiguration.setPassword(redisProperty.password());
//        return redisStandaloneConfiguration;
//    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration("userTokenCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(4)))
                .withCacheConfiguration("userCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(4)));
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(6))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
