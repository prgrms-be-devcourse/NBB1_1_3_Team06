package com.nbe2.domain.notification

import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository
    : JpaRepository<Notification, Long>, NotificationRepositoryCustom {
    fun existsByTargetIdAndIsRead(userId: Long, isRead: Boolean): Boolean
}
