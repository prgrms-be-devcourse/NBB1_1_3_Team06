package com.nbe2.api.notification.sse

import com.nbe2.domain.notification.NewNotificationEvent
import com.nbe2.domain.notification.NotificationSender
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException

@Component
class SseSender(private val sseEmitterRepository: SseEmitterRepository) : NotificationSender {

    override fun send(event: NewNotificationEvent) {
        val emitter = sseEmitterRepository.findById(event.targetId) ?: return

        try {
            emitter
                .send(
                    SseEmitter.event()
                        .id("")
                        .name(event.type.name)
                        .data("new notification sent")
                )
        } catch (e: IOException) {
            sseEmitterRepository.remove(event.targetId)
            emitter.completeWithError(e)
        }
    }
}
