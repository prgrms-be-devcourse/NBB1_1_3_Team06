package com.nbe2.domain.auth

import com.nbe2.domain.global.EMAIL
import com.nbe2.domain.global.PASSWORD
import com.nbe2.domain.user.UserReader
import com.nbe2.domain.user.createUserWithId
import com.nbe2.domain.user.exception.UserNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AuthenticatorTest :
        BehaviorSpec({
            val passwordEncoder = mockk<PasswordEncoder>()
            val userReader = mockk<UserReader>()

            val authenticator = Authenticator(passwordEncoder, userReader)

            Given("아이디, 비밀번호가 주어진 경우") {
                val login = Login(EMAIL, PASSWORD)
                val user = createUserWithId()
                val expected = UserPrincipal.of(user.id!!, user.role)

                every { userReader.read(any<Long>()) } returns user
                every {
                    passwordEncoder.isPasswordUnmatched(any(), any())
                } returns false

                When("로그인을 하면") {
                    val actual = authenticator.authenticate(login)

                    Then("인증에 성공한다.") { actual shouldBe expected }
                }
            }

            Given("이메일이 유효하지 않은 경우") {
                val login = Login(EMAIL, PASSWORD)

                every { userReader.read(any<Long>()) } throws
                        UserNotFoundException

                When("로그인을 하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<UserNotFoundException> {
                            authenticator.authenticate(login)
                        }
                    }
                }
            }

            Given("비밀번호가 유효하지 않은 경우") {
                val login = Login(EMAIL, PASSWORD)
                val user = createUserWithId()

                every { userReader.read(any<Long>()) } returns user
                every {
                    passwordEncoder.isPasswordUnmatched(any(), any())
                } returns true

                When("로그인을 하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<UserNotFoundException> {
                            authenticator.authenticate(login)
                        }
                    }
                }
            }
        })
