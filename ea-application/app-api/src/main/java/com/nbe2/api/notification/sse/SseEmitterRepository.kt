package com.nbe2.api.notification.sse

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Repository
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Repository
class SseEmitterRepository {
    private val emitters: MutableMap<Long, SseEmitter> = ConcurrentHashMap()

    fun save(userId: Long, emitter: SseEmitter) {
        emitters[userId] = emitter
    }

    fun findById(userId: Long): SseEmitter? = emitters[userId]

    fun remove(userId: Long) {
        emitters.remove(userId)
    }
}
