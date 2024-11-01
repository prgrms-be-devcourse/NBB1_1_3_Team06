package com.nbe2.domain.notification

import com.nbe2.common.dto.Cursor
import com.nbe2.common.dto.CursorResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationService(
        private val notificationUpdater: NotificationUpdater,
        private val notificationReader: NotificationReader,
) {

    @Transactional
    fun getNotificationHistory(
            userId: Long,
            cursor: Cursor,
    ): CursorResult<NotificationDetail> {
        val notifications = notificationReader.read(userId, cursor)
        val nextCursor = getNextCursor(userId, notifications)
        notificationUpdater.readAllUnreadNotifications(userId)
        return CursorResult(notifications, nextCursor)
    }

    fun hasUnreadNotification(userId: Long) =
            notificationReader.hasUnreadNotification(userId)

    private fun getNextCursor(
            userId: Long,
            notifications: List<NotificationDetail>,
    ): Long? {
        if (notifications.isEmpty()) return null

        return notificationReader.getNextCursor(
                userId,
                notifications[notifications.size - 1].notificationId,
        )
    }
}
