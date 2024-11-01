package com.nbe2.domain.notification

import com.nbe2.common.dto.Cursor
import com.nbe2.common.dto.CursorResult
import com.nbe2.domain.global.ID
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class NotificationServiceTest : BehaviorSpec({
    val notificationUpdater = mockk<NotificationUpdater>()
    val notificationReader = mockk<NotificationReader>()

    val notificationService = NotificationService(notificationUpdater, notificationReader)

    Given("커서가 주어지는 경우") {
        val userId = ID
        val cursor = Cursor(Long.MAX_VALUE, 2)
        val details = listOf(
            createCommentNotificationWithId(2),
            createCommentNotificationWithId(1)
        ).map(NotificationDetail::from)
        val expected = CursorResult(details, null)

        every { notificationReader.read(any(), any()) } returns details
        every { notificationReader.getNextCursor(any(), any()) } returns expected.nextCursor

        When("알림 기록을 조회하면") {
            val actual = notificationService.getNotificationHistory(userId, cursor)

            Then("기록이 포함된 커서 결과를 볼 수 있다.") {
                verify { notificationUpdater.readAllUnreadNotifications(userId) }
                actual shouldBe expected
            }
        }
    }

    Given("중간 커서가 주어지는 경우") {
        val userId = ID
        val cursor = Cursor(Long.MAX_VALUE, 2)
        val details = listOf(
            createCommentNotificationWithId(5),
            createCommentNotificationWithId(4)
        ).map(NotificationDetail::from)
        val expected = CursorResult(details, 3L)

        every { notificationReader.read(any(), any()) } returns details
        every { notificationReader.getNextCursor(any(), any()) } returns expected.nextCursor

        When("알림 기록을 조회하면") {
            val actual = notificationService.getNotificationHistory(userId, cursor)

            Then("다음 커서와 함께 커서 결과가 조회된다.") {
                verify { notificationUpdater.readAllUnreadNotifications(userId) }
                actual shouldBe expected
            }
        }
    }

    Given("알림 기록이 없는 경우") {
        val userId = ID
        val cursor = Cursor(Long.MAX_VALUE, 2)
        val expected = CursorResult(emptyList<NotificationDetail>(), null)

        every { notificationReader.read(any(), any()) } returns emptyList()

        When("알림 기록을 조회하면") {
            val actual = notificationService.getNotificationHistory(userId, cursor)

            Then("빈 커서 결과가 조회된다.") {
                verify { notificationUpdater.readAllUnreadNotifications(userId) }
                actual shouldBe expected
            }
        }
    }
})
