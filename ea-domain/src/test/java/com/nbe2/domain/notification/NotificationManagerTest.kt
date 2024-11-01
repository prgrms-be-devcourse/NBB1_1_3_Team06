package com.nbe2.domain.notification

import com.nbe2.domain.notification.NewNotificationEvent
import com.nbe2.domain.user.exception.UserNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

class NotificationManagerTest : BehaviorSpec({
    val notificationSender = mockk<NotificationSender>()
    val notificationAppender = mockk<NotificationAppender>()

    val notificationManager = NotificationManager(notificationAppender, notificationSender)

    Given("유효한 알림이 주어지는 경우") {
        val notification = createNewNotification()

        When("알림을 전송하면") {
            notificationManager.send(notification)

            Then("알림을 저장하고 이벤트를 전송한다.") {
                verify (exactly = 1) { notificationAppender.append(notification) }
                verify (exactly = 1) { notificationSender.send(any<NewNotificationEvent>()) }
            }
        }
    }

    Given("알림이 유효하지 않은 경우") {
        val notification = createNewNotification()

        every { notificationAppender.append(any<NewNotification>()) } throws UserNotFoundException

        When("알림을 전송하면") {
            Then("이벤트를 전송하지 않고 예외가 발생한다.") {
                shouldThrow<UserNotFoundException> { notificationManager.send(notification) }
                verify (exactly = 0) { notificationSender.send(any<NewNotificationEvent>()) }
            }
        }
    }
})
