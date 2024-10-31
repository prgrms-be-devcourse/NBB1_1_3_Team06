package com.nbe2.domain.notification

interface NotificationRepositoryCustom {
    fun findByUserIdWithCursor(userId: Long, cursor: Long, size: Int): List<Notification>

    fun findNextCursor(userId: Long, lastCursor: Long): Long?

    fun setIsRead(userId: Long, isRead: Boolean)

    fun removeByCreatedAtBefore(at: java.time.LocalDateTime)
}
