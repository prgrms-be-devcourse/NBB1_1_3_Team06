package com.nbe2.domain.emergencyroom

interface EmergencyRoomClient {
    fun getEmergencyRoomInfoData(): List<EmergencyRoomInfo>

    fun directionsEmergencyRoom(
            start: String,
            end: String,
    ): EmergencyRoomDirectionsInfo
}
