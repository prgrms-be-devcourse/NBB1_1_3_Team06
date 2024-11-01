package com.nbe2.domain.notification

import com.nbe2.common.dto.Cursor
import com.nbe2.domain.global.ID
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class NotificationReaderTest : BehaviorSpec({
    val notificationRepository = mockk<NotificationRepository>()

    val notificationReader = NotificationReader(notificationRepository)

    Given("알림 기록과 커서가 주어지는 경우") {
        val cursor = Cursor(50, 2)
        val notifications = List(2) { createCommentNotificationWithId(1) }
        val expected = notifications.stream().map(NotificationDetail::from).toList()

        every { notificationRepository.findByUserIdWithCursor(any<Long>(), any<Long>(), any<Int>()) } returns notifications

        When("알림 기록을 조회하면") {
            val actual = notificationReader.read(ID, cursor)

            Then("기록 상세 리스트를 볼 수 있다.") {
                actual shouldBe expected
            }
        }
    }

    Given("빈 기록과 커서가 주어지는 경우") {
        val cursor = Cursor(50, 2)
        val notifications = emptyList<Notification>()
        val expected = notifications.stream().map(NotificationDetail::from).toList()

        every { notificationRepository.findByUserIdWithCursor(any<Long>(), any<Long>(), any<Int>()) } returns notifications

        When("알림 기록을 조회하면") {
            val actual = notificationReader.read(ID, cursor)

            Then("상세 리스트가 존재하지 않는다.") {
                actual shouldBe expected
            }
        }
    }
})
