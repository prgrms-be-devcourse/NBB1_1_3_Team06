package com.nbe2.api.auth.dto

import com.nbe2.domain.user.UserProfile

data class SignupRequest(
        val name: String,
        val email: String,
        val password: String,
) {

    fun toUserProfile() = UserProfile(name, email, password)
}
