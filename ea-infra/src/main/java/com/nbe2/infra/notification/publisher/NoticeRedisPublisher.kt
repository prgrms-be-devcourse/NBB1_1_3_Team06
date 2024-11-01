package com.nbe2.infra.notification.publisher

import com.nbe2.domain.notice.NewNoticeOfBookmarkedHospitalEvent
import com.nbe2.domain.notice.NoticeEventPublisher
import com.nbe2.domain.notification.NotificationType
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class NoticeRedisPublisher(
        private val notificationTemplate: RedisTemplate<String, Any>
) : NoticeEventPublisher {

    override fun publish(event: NewNoticeOfBookmarkedHospitalEvent) {
        val topic = ChannelTopic(NotificationType.NOTICE.channelId)
        notificationTemplate.convertAndSend(topic.topic, event)
    }
}
