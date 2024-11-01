package com.nbe2.domain.user

import com.nbe2.domain.auth.PasswordEncoder
import com.nbe2.domain.user.exception.InvalidPasswordException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserUpdaterTest :
        BehaviorSpec({
            val passwordEncoder = mockk<PasswordEncoder>()
            val userRepository = mockk<UserRepository>()

            val userUpdater = UserUpdater(passwordEncoder, userRepository)

            Given("회원과 변경할 프로필이 있는 경우") {
                val profile = UpdateProfile("new name", "new email")
                val user = createUserWithId()

                When("프로필을 수정하면") {
                    userUpdater.update(user, profile)

                    Then("새 프로필로 변경된다.") {
                        user.name shouldBe profile.name
                        user.email shouldBe profile.email
                        verify(exactly = 1) { userRepository.save(user) }
                    }
                }
            }

            Given("회원과 변경할 비밀번호가 있는 경우") {
                val user = createUserWithId()
                val password = UpdatePassword(user.password, "new password")
                val encoded = "encoded password"

                every {
                    passwordEncoder.isPasswordUnmatched(any(), any())
                } returns false
                every { passwordEncoder.encode(any()) } returns encoded

                When("기존 비밀번호와 함께 새 비밀번호로 변경하면") {
                    userUpdater.update(user, password)

                    Then("새 비밀번호로 변경된다.") {
                        user.password shouldBe encoded
                        verify(exactly = 1) { userRepository.save(user) }
                    }
                }
            }

            Given("기존 비밀번호가 일치하지 않는 경우") {
                val user = createUserWithId()
                val password = UpdatePassword(user.password, "new password")
                val encoded = "encoded password"

                every {
                    passwordEncoder.isPasswordUnmatched(any(), any())
                } returns false
                every { passwordEncoder.encode(any()) } returns encoded

                When("비밀번호를 변경하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<InvalidPasswordException> {
                            userUpdater.update(user, password)
                        }
                    }
                }
            }
        })
