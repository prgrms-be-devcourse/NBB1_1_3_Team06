package com.nbe3.api.auth.dto

import com.nbe3.domain.user.UserProfileWithLicense

data class PendingUserResponse(val id: Long, val name: String, val email: String, val fileId: Long) {

    companion object {
        fun from(user: UserProfileWithLicense): PendingUserResponse {
            return PendingUserResponse(user.id, user.name, user.email, user.licenseId)
        }
    }
}
