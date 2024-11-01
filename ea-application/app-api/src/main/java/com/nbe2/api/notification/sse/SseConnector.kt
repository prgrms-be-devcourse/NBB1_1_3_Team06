package com.nbe2.api.notification.sse

import java.io.IOException
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Component
class SseConnector(private val sseEmitterRepository: SseEmitterRepository) {

    fun connect(userId: Long): SseEmitter {
        val emitter = SseEmitter(TIMEOUT)
        sseEmitterRepository.save(userId, emitter)
        emitter.onTimeout { sseEmitterRepository.remove(userId) }
        emitter.onCompletion { sseEmitterRepository.remove(userId) }

        try {
            emitter.send(
                    SseEmitter.event()
                            .id("")
                            .name(CONNECTION_NAME)
                            .data("emitter connected")
            )
        } catch (e: IOException) {
            sseEmitterRepository.remove(userId)
            emitter.completeWithError(e)
        }

        return emitter
    }

    companion object {
        const val CONNECTION_NAME: String = "CONNECT"
        const val TIMEOUT: Long = 30 * 60 * 1000L
    }
}
