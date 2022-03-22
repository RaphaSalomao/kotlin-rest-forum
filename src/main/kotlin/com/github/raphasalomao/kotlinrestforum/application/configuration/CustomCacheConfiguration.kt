package com.github.raphasalomao.kotlinrestforum.application.configuration

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration

@Configuration
class CustomCacheConfiguration {

    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
    }

    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        return RedisCacheManagerBuilderCustomizer {
            it.withCacheConfiguration(
                "firstCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
            ).withCacheConfiguration(
                "secondCache",
                RedisCacheConfiguration.defaultCacheConfig(
                    // needed for evade ClassCastException when deserializing objects by redis
                    Thread.currentThread().contextClassLoader
                ).entryTtl(Duration.ofMinutes(5))
            )
        }
    }

}