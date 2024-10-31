package com.nbe2.infra.notification.publisher

import com.nbe2.domain.notification.NotificationType
import com.nbe2.domain.post.CommentEventPublisher
import com.nbe2.domain.post.NewCommentEvent
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisPublisher(private val notificationTemplate: RedisTemplate<String, Any>) : CommentEventPublisher {

    override fun publish(event: NewCommentEvent) {
        val topic = ChannelTopic(NotificationType.COMMENT.channelId)
        notificationTemplate.convertAndSend(topic.topic, event)
    }
}
