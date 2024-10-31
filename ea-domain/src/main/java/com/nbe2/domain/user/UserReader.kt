package com.nbe2.domain.user

import com.nbe2.common.dto.PageResult
import com.nbe2.domain.user.exception.UserNotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class UserReader(private val userRepository: UserRepository) {

    fun read(userId: Long) =
        userRepository.findById(userId)
            .orElseThrow { UserNotFoundException }

    fun read(email: String) = userRepository.findByEmail(email) ?: throw UserNotFoundException

    fun read(pageable: Pageable): PageResult<UserProfileWithLicense> {
        val userPage = userRepository.findPageByApprovalStatus(ApprovalStatus.PENDING, pageable)
        return PageResult(
            userPage.content, userPage.totalPages, userPage.hasNext()
        )
    }
}
