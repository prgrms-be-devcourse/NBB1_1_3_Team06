package com.nbe3.api.user.dto

import com.nbe3.domain.user.MedicalProfile

data class MedicalRequest(val emergencyRoomId: Long, val licenseId: Long) {

    fun toMedicalProfile(): MedicalProfile {
        return MedicalProfile(emergencyRoomId, licenseId)
    }
}
