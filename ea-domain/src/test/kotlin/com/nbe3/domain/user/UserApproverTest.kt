package com.nbe3.domain.user

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify

class UserApproverTest : BehaviorSpec({
    val userRepository = mockk<UserRepository>()

    val userApprover = UserApprover(userRepository)

    Given("가입 대기 상태인 회원이 있는 경우") {
        val user = createPendingUser()

        When("가입 승인을 하면") {
            userApprover.approve(user)

            Then("승인된 회원을 저장한다.") {
                user.approvalStatus shouldBe ApprovalStatus.APPROVED
                verify(exactly = 1) { userRepository.save(any<User>()) }
            }
        }
    }
})
