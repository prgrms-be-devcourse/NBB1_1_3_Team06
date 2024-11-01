package com.nbe2.domain.notification

data class NewNotificationEvent(
        val targetId: Long,
        val type: NotificationType,
) {
    companion object {
        fun of(targetId: Long, type: NotificationType) =
                NewNotificationEvent(targetId, type)
    }
}
