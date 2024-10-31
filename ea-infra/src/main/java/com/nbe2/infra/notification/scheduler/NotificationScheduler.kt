package com.nbe2.infra.notification.scheduler

import com.nbe2.common.logger
import com.nbe2.domain.notification.NotificationDeleter
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NotificationScheduler(private val notificationDeleter: NotificationDeleter) {
    private val log = logger()

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    @SchedulerLock(name = "notification", lockAtLeastFor = "PT2S", lockAtMostFor = "PT2S")
    fun deleteOutdatedNotifications() {
        log.info("Deleting outdated notifications")
        notificationDeleter.deleteOutdatedNotifications()
    }
}
