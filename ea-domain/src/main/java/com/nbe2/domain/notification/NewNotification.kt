package com.nbe2.domain.notification

data class NewNotification(
    val targetId: Long,
    val referenceUri: String,
    val title: String,
    val type: NotificationType
) {
    companion object {
        fun of(
            targetId: Long, referenceUri: String, title: String, type: NotificationType
        ) = NewNotification(targetId, referenceUri, title, type)
    }
}
