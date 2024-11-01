package com.nbe2.domain.user

import com.nbe2.domain.global.EMAIL
import com.nbe2.domain.user.exception.AlreadyExistsEmailException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class UserValidatorTest :
        BehaviorSpec({
            val userRepository = mockk<UserRepository>()

            val userValidator = UserValidator(userRepository)

            Given("이메일이 중복되지 않은 경우") {
                val email = EMAIL

                every { userRepository.existsByEmail(email) } returns false

                When("중복 조회를 하면") {
                    Then("예외가 발생하지 않는다.") {
                        shouldNotThrow<AlreadyExistsEmailException> {
                            userValidator.validate(email)
                        }
                    }
                }
            }

            Given("이메일이 중복되는 경우") {
                val email = EMAIL

                every { userRepository.existsByEmail(email) } returns true

                When("중복 조회를 하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<AlreadyExistsEmailException> {
                            userValidator.validate(email)
                        }
                    }
                }
            }
        })
