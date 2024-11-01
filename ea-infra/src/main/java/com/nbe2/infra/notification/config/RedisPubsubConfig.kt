package com.nbe2.infra.notification.config

import com.nbe2.domain.notification.NotificationType
import com.nbe2.infra.notification.subscriber.CommentRedisSubscriber
import com.nbe2.infra.notification.subscriber.NoticeRedisSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisPubsubConfig(
        private val redisConnectionFactory: RedisConnectionFactory
) {

    @Bean
    fun redisMessageListenerContainer(
            noticeListener: MessageListenerAdapter,
            commentListener: MessageListenerAdapter,
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        container.addMessageListener(
                noticeListener,
                PatternTopic(NotificationType.NOTICE.channelId),
        )
        container.addMessageListener(
                commentListener,
                PatternTopic(NotificationType.COMMENT.channelId),
        )
        return container
    }

    @Bean
    fun noticeListener(noticeRedisSubscriber: NoticeRedisSubscriber) =
            MessageListenerAdapter(noticeRedisSubscriber)

    @Bean
    fun commentListener(commentRedisSubscriber: CommentRedisSubscriber) =
            MessageListenerAdapter(commentRedisSubscriber)

    @Bean
    fun notificationTemplate(): RedisTemplate<String, Any> {
        val redisTemplate: RedisTemplate<String, Any> =
                RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer =
                Jackson2JsonRedisSerializer(Any::class.java)
        return redisTemplate
    }
}
