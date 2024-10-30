package com.nbe3.domain.auth

import com.nbe3.common.dto.Page
import com.nbe3.common.dto.PageResult
import com.nbe3.domain.global.util.PagingUtil
import com.nbe3.domain.user.User
import com.nbe3.domain.user.UserApprover
import com.nbe3.domain.user.UserProfileWithLicense
import com.nbe3.domain.user.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminAuthService(
    private val userReader: UserReader,
    private val userApprover: UserApprover
) {

    @Transactional
    fun approveSignup(userId: Long) {
        val user: User = userReader.read(userId)
        userApprover.approve(user)
    }

    @Transactional(readOnly = true)
    fun searchPendingUsers(page: Page): PageResult<UserProfileWithLicense> {
        return userReader.read(PagingUtil.toPageRequest(page))
    }
}
