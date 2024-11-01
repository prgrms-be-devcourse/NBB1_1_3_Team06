package com.nbe2.domain.notification

import com.nbe2.domain.user.UserReader
import com.nbe2.domain.user.createUserWithId
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class NotificationAppenderTest :
        BehaviorSpec({
            val userReader = mockk<UserReader>()
            val notificationRepository = mockk<NotificationRepository>()

            val appender =
                    NotificationAppender(
                            notificationRepository = notificationRepository,
                            userReader = userReader,
                    )

            Given("사용자와 알림이 주어지는 경우") {
                val notification = createNewNotification()
                val user = createUserWithId()

                every { userReader.read(any<Long>()) } returns user

                When("알림을 저장하면") {
                    appender.append(notification)

                    Then("알림을 DB에 저장한다.") {
                        verify(exactly = 1) {
                            notificationRepository.save(any<Notification>())
                        }
                    }
                }
            }
        })
