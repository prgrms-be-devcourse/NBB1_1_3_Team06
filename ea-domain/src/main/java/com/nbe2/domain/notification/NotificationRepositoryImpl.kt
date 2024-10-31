package com.nbe2.domain.notification

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class NotificationRepositoryImpl(private val queryFactory: JPAQueryFactory) : NotificationRepositoryCustom {

    override fun findByUserIdWithCursor(userId: Long, cursor: Long, size: Int): List<Notification> {
        return queryFactory
            .selectFrom(QNotification.notification)
            .where(QNotification.notification.target.id.eq(userId))
            .where(QNotification.notification.id.loe(cursor))
            .orderBy(QNotification.notification.id.desc())
            .limit(size.toLong())
            .fetch()
    }

    override fun findNextCursor(userId: Long, lastCursor: Long): Long? {
        return queryFactory
            .select(QNotification.notification.id)
            .from(QNotification.notification)
            .where(QNotification.notification.target.id.eq(userId))
            .where(QNotification.notification.id.lt(lastCursor))
            .orderBy(QNotification.notification.id.desc())
            .fetchFirst()
    }

    override fun setIsRead(userId: Long, isRead: Boolean) {
        queryFactory
            .update(QNotification.notification)
            .set(QNotification.notification.isRead, isRead)
            .where(QNotification.notification.target.id.eq(userId))
            .where(QNotification.notification.isRead.eq(!isRead))
            .execute()
    }

    override fun removeByCreatedAtBefore(at: LocalDateTime) {
        queryFactory.delete(QNotification.notification).where(QNotification.notification.createdAt.before(at))
            .execute()
    }
}
