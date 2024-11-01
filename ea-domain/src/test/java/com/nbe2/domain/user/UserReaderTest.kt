package com.nbe2.domain.user

import com.nbe2.domain.global.EMAIL
import com.nbe2.domain.global.ID
import com.nbe2.domain.user.exception.UserNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class UserReaderTest :
        BehaviorSpec({
            val userRepository = mockk<UserRepository>()

            val userReader = UserReader(userRepository)

            Given("유효한 ID가 주어지는 경우") {
                val userId = ID
                val expected = createUser()

                every { userRepository.findById(any()) } returns
                        Optional.of(expected)

                When("ID로 사용자를 조회하면") {
                    val actual = userReader.read(userId)

                    Then("사용자가 조회된다.") { actual shouldBe expected }
                }
            }

            Given("회원이 존재하지 않는 경우") {
                val userId = ID

                every { userRepository.findById(any()) } returns
                        Optional.empty()

                When("ID로 사용자를 조회하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<UserNotFoundException> {
                            userReader.read(userId)
                        }
                    }
                }
            }

            Given("유효한 이메일이 주어지는 경우") {
                val email = EMAIL
                val expected = createUser()

                every { userRepository.findByEmail(any()) } returns expected

                When("이메일로 사용자를 조회하면") {
                    val actual = userReader.read(email)

                    Then("사용자가 조회된다.") { actual shouldBe expected }
                }
            }

            Given("존재하지 않는 이메일이 주어지는 경우") {
                val email = EMAIL

                every { userRepository.findByEmail(any()) } returns null

                When("이메일로 사용자를 조회하면") {
                    Then("예외가 발생한다.") {
                        shouldThrow<UserNotFoundException> {
                            userReader.read(email)
                        }
                    }
                }
            }

            Given("승인 대기 상태의 사용자가 존재하지 않는 경우") {
                val pageable = PageRequest.of(0, 10)
                val emptyPage =
                        PageImpl(
                                emptyList<UserProfileWithLicense>(),
                                pageable,
                                0,
                        )

                every {
                    userRepository.findPageByApprovalStatus(
                            any<ApprovalStatus>(),
                            any(),
                    )
                } returns emptyPage

                When("사용자 목록을 조회하면") {
                    val actual = userReader.read(pageable)

                    Then("빈 페이지가 반환된다.") {
                        actual.content.shouldBeEmpty()
                        actual.totalPage shouldBe 0
                        actual.hasNext.shouldBeFalse()
                    }
                }
            }

            Given("승인 대기 상태의 사용자가 존재하는 경우") {
                val pageable = PageRequest.of(0, 5)
                val content = List(10) { createUserProfileWithLicense() }
                val emptyPage =
                        PageImpl(content, pageable, content.size.toLong())

                every {
                    userRepository.findPageByApprovalStatus(
                            any<ApprovalStatus>(),
                            any(),
                    )
                } returns emptyPage

                When("사용자 목록을 조회하면") {
                    val actual = userReader.read(pageable)

                    Then("사용자 목록이 포함된 페이지가 반환된다.") {
                        actual.content.size shouldBe content.size
                        actual.totalPage shouldBe 2
                        actual.hasNext.shouldBeTrue()
                    }
                }
            }

            Given("승인 대기 상태의 사용자가 마지막 페이지에 있는 경우") {
                val pageable = PageRequest.of(0, 5)
                val content = List(5) { createUserProfileWithLicense() }
                val page = PageImpl(content, pageable, content.size.toLong())

                every {
                    userRepository.findPageByApprovalStatus(
                            any<ApprovalStatus>(),
                            any(),
                    )
                } returns page

                When("사용자 목록을 조회하면") {
                    val actual = userReader.read(pageable)

                    Then("마지막 페이지가 반환되고 다음 페이지는 없다.") {
                        actual.content.size shouldBe content.size
                        actual.totalPage shouldBe 1
                        actual.hasNext.shouldBeFalse()
                    }
                }
            }
        })
