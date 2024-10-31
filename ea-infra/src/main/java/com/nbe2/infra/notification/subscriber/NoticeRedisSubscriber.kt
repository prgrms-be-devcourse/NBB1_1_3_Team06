package com.nbe2.infra.notification.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import com.nbe2.common.logger
import com.nbe2.domain.notice.NewNoticeOfBookmarkedHospitalEvent
import com.nbe2.domain.notification.NewNotification
import com.nbe2.domain.notification.NotificationManager
import com.nbe2.domain.notification.NotificationType
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class NoticeRedisSubscriber(
    private val notificationManager: NotificationManager,
    private val objectMapper: ObjectMapper
) : MessageListener {

    private val log = logger()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val event =
                objectMapper.readValue(message.body, NewNoticeOfBookmarkedHospitalEvent::class.java)
            log.info("Notice event published: {}, to {}", event.referenceUri, event.targetId)
            notificationManager.send(
                NewNotification.of(
                    targetId = event.targetId,
                    referenceUri = event.referenceUri,
                    title = event.hospitalName,
                    type = NotificationType.NOTICE
                )
            )
        } catch (e: Exception) {
            log.error(e.message)
        }
    }
}
