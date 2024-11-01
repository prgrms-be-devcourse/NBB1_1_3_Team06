package com.nbe2.domain.user

import com.nbe2.domain.auth.PasswordEncoder
import com.nbe2.domain.auth.createOAuthProfile
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserAppenderTest :
        BehaviorSpec({
            val passwordEncoder = mockk<PasswordEncoder>()
            val userRepository = mockk<UserRepository>()

            val userAppender = UserAppender(passwordEncoder, userRepository)

            Given("일반 회원 정보가 주어지는 경우") {
                val userProfile = createUserProfile()

                every { passwordEncoder.encode(any()) } returns
                        "encoded password"

                When("회원을 추가하면") {
                    userAppender.append(userProfile)

                    Then("회원을 저장한다.") {
                        verify(exactly = 1) { userRepository.save(any<User>()) }
                    }
                }
            }

            Given("관계자 회원 정보가 주어지는 경우") {
                val oauthProfile = createOAuthProfile()

                every { passwordEncoder.encode(any()) } returns
                        "encoded password"

                When("회원을 추가하면") {
                    userAppender.append(oauthProfile)

                    Then("회원을 저장한다.") {
                        verify(exactly = 1) { userRepository.save(any<User>()) }
                    }
                }
            }
        })
