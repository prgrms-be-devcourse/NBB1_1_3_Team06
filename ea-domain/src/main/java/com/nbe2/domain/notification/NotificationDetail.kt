package com.nbe2.domain.notification

data class NotificationDetail(
    val notificationId: Long,
    val referenceUri: String,
    val title: String,
    val type: NotificationType
) {
    companion object {
        fun from(notification: Notification) = NotificationDetail(
            notificationId = notification.id!!,
            referenceUri = notification.referenceUri,
            title = notification.title,
            type = notification.type
        )
    }
}
