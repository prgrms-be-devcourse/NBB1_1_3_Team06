package com.nbe3.api.user.dto

import com.nbe3.domain.user.UpdateProfile

data class UpdateProfileRequest(val name: String, val email: String) {

    fun toProfile(): UpdateProfile {
        return UpdateProfile(name, email)
    }
}
