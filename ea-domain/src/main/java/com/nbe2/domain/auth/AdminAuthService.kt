package com.nbe2.domain.auth

import com.nbe2.common.dto.Page
import com.nbe2.common.dto.PageResult
import com.nbe2.domain.global.util.PagingUtil
import com.nbe2.domain.user.UserApprover
import com.nbe2.domain.user.UserProfileWithLicense
import com.nbe2.domain.user.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminAuthService(
        private val userReader: UserReader,
        private val userApprover: UserApprover,
) {

    @Transactional
    fun approveSignup(userId: Long) {
        val user = userReader.read(userId)
        userApprover.approve(user)
    }

    @Transactional(readOnly = true)
    fun searchPendingUsers(page: Page): PageResult<UserProfileWithLicense> =
            userReader.read(PagingUtil.toPageRequest(page))
}
