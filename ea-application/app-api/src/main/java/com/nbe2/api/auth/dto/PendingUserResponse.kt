package com.nbe2.api.auth.dto

import com.nbe2.domain.user.UserProfileWithLicense

data class PendingUserResponse(
        val id: Long,
        val name: String,
        val email: String,
        val fileId: Long,
) {

    companion object {
        fun from(user: UserProfileWithLicense) =
                PendingUserResponse(
                        user.id,
                        user.name,
                        user.email,
                        user.licenseId,
                )
    }
}
