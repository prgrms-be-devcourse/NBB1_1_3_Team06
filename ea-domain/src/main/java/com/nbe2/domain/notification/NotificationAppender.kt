package com.nbe2.domain.notification

import com.nbe2.domain.user.UserReader
import org.springframework.stereotype.Component

@Component
class NotificationAppender(
    private val userReader: UserReader,
    private val notificationRepository: NotificationRepository
) {

    fun append(notification: NewNotification) {
        notificationRepository.save(
            Notification.of(
                userReader.read(notification.targetId),
                notification.referenceUri,
                notification.title,
                notification.type
            )
        )
    }
}
