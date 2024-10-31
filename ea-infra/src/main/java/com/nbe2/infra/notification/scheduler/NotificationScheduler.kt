package com.nbe2.infra.notification.scheduler

import com.nbe2.common.logger
import com.nbe2.domain.notification.NotificationDeleter
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@RequiredArgsConstructor
@Slf4j
class NotificationScheduler {
    private val log = logger()
    private val notificationDeleter: NotificationDeleter? = null

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    @SchedulerLock(name = "notification", lockAtLeastFor = "PT2S", lockAtMostFor = "PT2S")
    fun deleteOutdatedNotifications() {
        log.info("Deleting outdated notifications")
        notificationDeleter!!.deleteOutdatedNotifications()
    }
}
