package com.nbe2.domain.notification

interface NotificationSender {
    fun send(event: NewNotificationEvent)
}
