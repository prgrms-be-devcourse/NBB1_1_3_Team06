package com.nbe2.api.emergencyroom.dto

import com.nbe2.domain.emergencyroom.EmergencyRoomMapInfo

data class EmergencyRoomMapResponse(
    val emergencyRoomId: Long,
    val hpId: String,
    val hospitalName: String,
    val address: String,
    val simpleMap: String,
    val longitude: Double,
    val latitude: Double,
    val distance: Double
) {
    companion object {
        fun from(mapInfo: EmergencyRoomMapInfo): EmergencyRoomMapResponse {
            return EmergencyRoomMapResponse(
                mapInfo.emergencyRoomId,
                mapInfo.hpId,
                mapInfo.hospitalName,
                mapInfo.address,
                mapInfo.simpleMap,
                mapInfo.coordinate.longitude!!,
                mapInfo.coordinate.latitude!!,
                convertToKilometers(mapInfo.distance)
            )
        }

        private fun convertToKilometers(distanceInMeters: Double): Double {
            return Math.round((distanceInMeters / 1000) * 100) / 100.0
        }
    }
}
