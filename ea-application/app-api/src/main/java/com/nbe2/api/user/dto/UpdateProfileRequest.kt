package com.nbe2.api.user.dto

import com.nbe2.domain.user.UpdateProfile

data class UpdateProfileRequest(val name: String, val email: String) {

    fun toProfile() = UpdateProfile(name, email)
}
