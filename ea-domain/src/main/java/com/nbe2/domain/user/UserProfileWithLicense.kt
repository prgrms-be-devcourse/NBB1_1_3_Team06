package com.nbe2.domain.user

data class UserProfileWithLicense(
        val id: Long,
        val name: String,
        val email: String,
        val licenseId: Long,
)
