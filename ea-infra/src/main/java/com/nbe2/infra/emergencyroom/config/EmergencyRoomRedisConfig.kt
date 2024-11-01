package com.nbe2.infra.emergencyroom.config

import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class EmergencyRoomRedisConfig(
        private val redisConnectionFactory: RedisConnectionFactory
) {

    @Bean
    fun realTimeEmergencyInfoRedisTemplate():
            RedisTemplate<String, RealTimeEmergencyRoomInfo> {
        val redisTemplate = RedisTemplate<String, RealTimeEmergencyRoomInfo>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer =
                Jackson2JsonRedisSerializer(
                        RealTimeEmergencyRoomInfo::class.java
                )
        return redisTemplate
    }

    companion object {
        val REAL_TIME_INFO_TTL: Duration = Duration.ofMinutes(3)
    }
}
