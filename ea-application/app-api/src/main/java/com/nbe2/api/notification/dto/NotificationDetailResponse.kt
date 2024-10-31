package com.nbe2.api.notification.dto

import com.nbe2.domain.notification.NotificationDetail

data class NotificationDetailResponse(
    val notificationId: Long,
    val referenceUri: String,
    val title: String,
    val type: String
) {
    companion object {
        fun from(detail: NotificationDetail) =
            NotificationDetailResponse(
                notificationId = detail.notificationId,
                referenceUri = detail.referenceUri,
                title = detail.title,
                type = detail.type.name
            )
    }
}
