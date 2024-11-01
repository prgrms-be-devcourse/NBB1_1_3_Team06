package com.nbe2.domain.emergencyroom

interface EmergencyRoomRepositoryCustom {
    fun findByCoordinateAndDistance(
            coordinate: Coordinate,
            distance: Double,
    ): List<EmergencyRoomMapInfo>
}
