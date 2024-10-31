package com.nbe2.domain.notification

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NotificationDeleter(private val notificationRepository: NotificationRepository) {

    fun deleteOutdatedNotifications() {
        notificationRepository.removeByCreatedAtBefore(
            LocalDateTime.now().minusDays(OUTDATED_CRITERIA.toLong())
        )
    }

    companion object {
        const val OUTDATED_CRITERIA = 3 // 3 days
    }
}
