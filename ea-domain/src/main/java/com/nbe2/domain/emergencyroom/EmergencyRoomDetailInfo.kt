package com.nbe2.domain.emergencyroom

data class EmergencyRoomDetailInfo(
        val hospitalName: String,
        val address: String,
        val emergencyRoomContactNumber: String,
        val medicalDepartments: String,
        val emergencyRoomBedCount: Int, // 실시간 정보
        val availableBeds: Int,
        val operatingRoomBeds: Int,
        val isCtAvailable: Boolean,
        val isMriAvailable: Boolean,
        val isAngiographyAvailable: Boolean,
        val isVentilatorAvailable: Boolean,
        val isIncubatorAvailable: Boolean,
        val isAmbulanceAvailable: Boolean,
        val distance: Double,
) {
    companion object {
        fun create(
                emergencyRoom: EmergencyRoom,
                realTimeEmergencyRoomWithDistance:
                        RealTimeEmergencyRoomWithDistance,
        ): EmergencyRoomDetailInfo {
            return EmergencyRoomDetailInfo(
                    emergencyRoom.hospitalName,
                    emergencyRoom.address,
                    emergencyRoom.emergencyRoomContactNumber,
                    emergencyRoom.medicalDepartments,
                    emergencyRoom.bedCount.totalBedCount,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .availableBeds,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .operatingRoomBeds,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isCtAvailable,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isMriAvailable,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isAngiographyAvailable,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isVentilatorAvailable,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isIncubatorAvailable,
                    realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo
                            .isAmbulanceAvailable,
                    realTimeEmergencyRoomWithDistance.distance,
            )
        }
    }
}
