package com.nbe2.api.user.dto

import com.nbe2.domain.user.MedicalProfile

data class MedicalRequest(val emergencyRoomId: Long, val licenseId: Long) {

    fun toMedicalProfile() = MedicalProfile(emergencyRoomId, licenseId)
}
