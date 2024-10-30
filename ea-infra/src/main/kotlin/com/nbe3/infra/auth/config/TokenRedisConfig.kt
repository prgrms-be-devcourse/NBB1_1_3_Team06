package com.nbe3.infra.auth.config

import com.nbe3.domain.auth.RefreshToken
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class TokenRedisConfig(private val redisConnectionFactory: RedisConnectionFactory) {

    @Bean
    fun refreshTokenRedisTemplate(): RedisTemplate<String, RefreshToken> {
        val redisTemplate: RedisTemplate<String, RefreshToken> = RedisTemplate<String, RefreshToken>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(RefreshToken::class.java)
        return redisTemplate
    }
}
