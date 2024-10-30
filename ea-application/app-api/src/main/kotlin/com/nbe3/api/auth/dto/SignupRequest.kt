package com.nbe3.api.auth.dto

import com.nbe3.domain.user.UserProfile

data class SignupRequest(val name: String, val email: String, val password: String) {

    fun toUserProfile(): UserProfile {
        return UserProfile(name, email, password)
    }
}
