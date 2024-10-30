package com.nbe3.domain.user

import com.nbe3.common.dto.PageResult
import com.nbe3.domain.user.exception.UserNotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class UserReader(private val userRepository: UserRepository) {

    fun read(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException.EXCEPTION }
    }

    fun read(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException.EXCEPTION
    }

//    fun read(pageable: Pageable): PageResult<UserProfileWithLicense> {
//        val userPage: org.springframework.data.domain.Page<UserProfileWithLicense> =
//            userRepository.findPageByApprovalStatus(ApprovalStatus.PENDING, pageable!!)
//        return PageResult(
//            userPage.content, userPage.totalPages, userPage.hasNext()
//        )
//    }
}
