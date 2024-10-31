package com.nbe2.domain.notification

import org.springframework.stereotype.Component

@Component
class NotificationManager(
    private val notificationAppender: NotificationAppender,
    private val notificationSender: NotificationSender
) {

    fun send(notification: NewNotification) {
        notificationAppender.append(notification)
        notificationSender.send(
            NewNotificationEvent.of(notification.targetId, notification.type)
        )
    }
}
