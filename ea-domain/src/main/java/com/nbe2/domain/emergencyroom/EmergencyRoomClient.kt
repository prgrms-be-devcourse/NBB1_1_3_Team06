package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo
import com.nbe2.domain.emergencyroom.Region

interface EmergencyRoomClient {
    fun getRealTimeInfo(region: Region): List<RealTimeEmergencyRoomInfo>
    fun getEmergencyRoomInfoData(): List<EmergencyRoomInfo>
    fun directionsEmergencyRoom(start: String, end: String): EmergencyRoomDirectionsInfo
}
