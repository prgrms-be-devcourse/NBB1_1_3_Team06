package com.nbe2.domain.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserRepositoryCustom {
    fun findPageByApprovalStatus(
            approvalStatus: ApprovalStatus,
            pageable: Pageable,
    ): Page<UserProfileWithLicense>
}
