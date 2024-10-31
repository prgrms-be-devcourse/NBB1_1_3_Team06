package com.nbe2.domain.notification

import com.nbe2.common.dto.Cursor
import org.springframework.stereotype.Component

@Component
class NotificationReader(private val notificationRepository: NotificationRepository) {

    fun read(userId: Long, cursor: Cursor): List<NotificationDetail> = 
        notificationRepository
            .findByUserIdWithCursor(userId, cursor.cursor, cursor.size)
            .stream()
            .map { notification: Notification -> NotificationDetail.from(notification) }
            .toList()

    fun getNextCursor(userId: Long, lastCursor: Long) = 
        notificationRepository.findNextCursor(userId, lastCursor)

    fun hasUnreadNotification(userId: Long) = 
        notificationRepository.existsByTargetIdAndIsRead(userId, false)
}
