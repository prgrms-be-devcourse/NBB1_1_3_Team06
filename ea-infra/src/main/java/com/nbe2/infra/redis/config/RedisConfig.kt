package com.nbe2.infra.redis.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(basePackages = ["com.nbe2.infra"])
class RedisConfig @Autowired constructor(private val env: Environment) {

    private val host: String get() = env.getProperty("redis.host") ?: "localhost"
    private val port: Int get() = env.getProperty("redis.port")?.toInt() ?: 6379

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }
}
