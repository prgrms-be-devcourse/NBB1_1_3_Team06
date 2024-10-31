package com.nbe2.api.user.dto

import com.nbe2.domain.user.MyProfile

data class ProfileResponse(val name: String, val email: String, val hasMedicalAuthority: Boolean) {

    companion object {
        fun from(profile: MyProfile) = ProfileResponse(profile.name, profile.email, profile.hasMedicalAuthority)
    }
}
