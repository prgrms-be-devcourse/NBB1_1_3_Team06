package com.nbe2.domain.notification

import org.springframework.stereotype.Component

@Component
class NotificationUpdater(private val notificationRepository: NotificationRepository) {

    fun readAllUnreadNotifications(userId: Long) {
        notificationRepository.setIsRead(userId, true)
    }
}
