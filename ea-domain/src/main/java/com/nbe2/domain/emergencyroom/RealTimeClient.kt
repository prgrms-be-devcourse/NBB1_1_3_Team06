package com.nbe2.domain.emergencyroom

interface RealTimeClient {
    fun getRealTimeInfo(region: Region): List<RealTimeEmergencyRoomInfo>
}
