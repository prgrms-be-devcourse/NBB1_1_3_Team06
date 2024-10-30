package com.nbe3.api.user.dto

import com.nbe3.domain.user.MyProfile

data class ProfileResponse(val name: String, val email: String, val hasMedicalAuthority: Boolean) {

    companion object {
        fun from(profile: MyProfile): ProfileResponse {
            return ProfileResponse(profile.name, profile.email, profile.hasMedicalAuthority)
        }
    }
}
