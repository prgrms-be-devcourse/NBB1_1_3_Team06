package com.nbe2.infra.notification.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import com.nbe2.common.logger
import com.nbe2.domain.notification.NewNotification
import com.nbe2.domain.notification.NotificationManager
import com.nbe2.domain.notification.NotificationType
import com.nbe2.domain.posts.NewCommentEvent
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
@Slf4j
class CommentRedisSubscriber : MessageListener {
    private val notificationManager: NotificationManager? = null
    private val objectMapper: ObjectMapper? = null
    private val log = logger()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val event =
                objectMapper!!.readValue(message.body, NewCommentEvent::class.java)
            log.info(
                "Comment event published: {}, to {}",
                event.referenceUri,
                event.targetId
            )
            notificationManager!!.send(
                NewNotification.of(
                    event.targetId,
                    event.referenceUri,
                    event.postTitle,
                    NotificationType.COMMENT
                )
            )
        } catch (e: Exception) {
            log.error(e.message)
        }
    }
}
