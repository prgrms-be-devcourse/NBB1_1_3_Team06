package com.nbe2.domain.emergencyroom

data class RealTimeEmergencyRoomWithDistance(
        val realTimeEmergencyRoomInfo: RealTimeEmergencyRoomInfo,
        val distance: Double,
) {
    companion object {
        fun of(
                realTimeEmergencyRoomInfo: RealTimeEmergencyRoomInfo,
                distance: Double,
        ): RealTimeEmergencyRoomWithDistance {
            return RealTimeEmergencyRoomWithDistance(
                    realTimeEmergencyRoomInfo,
                    distance,
            )
        }
    }
}
