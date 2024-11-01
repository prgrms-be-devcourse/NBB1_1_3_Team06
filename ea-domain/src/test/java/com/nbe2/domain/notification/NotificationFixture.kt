package com.nbe2.domain.notification

import com.nbe2.domain.global.ID
import com.nbe2.domain.user.createUserWithId

const val REFERENCE_URI: String = "reference uri"
const val TITLE: String = "notification"

fun createCommentNotification() =
        Notification.of(
                target = createUserWithId(),
                referenceUri = REFERENCE_URI,
                type = NotificationType.COMMENT,
                title = TITLE,
        )

fun createCommentNotificationWithId(id: Long): Notification {
    val notification = createCommentNotification()

    try {
        val field = notification.javaClass.getDeclaredField("id")
        field.isAccessible = true
        field[notification] = id
    } catch (ignored: Exception) {}

    return notification
}

fun createNewNotification() =
        NewNotification.of(ID, REFERENCE_URI, TITLE, NotificationType.COMMENT)
