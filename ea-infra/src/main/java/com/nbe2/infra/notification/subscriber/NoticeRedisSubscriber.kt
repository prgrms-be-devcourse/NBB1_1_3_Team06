package com.nbe2.infra.notification.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import com.nbe2.common.logger
import com.nbe2.domain.notice.NewNoticeOfBookmarkedHospitalEvent
import com.nbe2.domain.notification.NewNotification
import com.nbe2.domain.notification.NotificationManager
import com.nbe2.domain.notification.NotificationType
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
@Slf4j
class NoticeRedisSubscriber : MessageListener {

    private val log = logger()
    private val notificationManager: NotificationManager? = null
    private val objectMapper: ObjectMapper? = null

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val event =
                objectMapper!!.readValue(
                    message.body, NewNoticeOfBookmarkedHospitalEvent::class.java
                )
            log.info(
                "Notice event published: {}, to {}",
                event.referenceUri,
                event.targetId
            )
            notificationManager!!.send(
                NewNotification.of(
                    event.targetId,
                    event.referenceUri,
                    event.hospitalName,
                    NotificationType.NOTICE
                )
            )
        } catch (e: Exception) {
            log.error(e.message)
        }
    }
}
