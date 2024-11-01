package com.nbe2.domain.emergencyroom

import java.awt.SystemColor.info
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class DistanceCalculator(private val emergencyRoomReader: EmergencyRoomReader) {
    fun calculate(
            realTimeEmergencyRoomInfos: List<RealTimeEmergencyRoomInfo>,
            currentCoordinate: Coordinate,
    ): List<RealTimeEmergencyRoomWithDistance> {
        return realTimeEmergencyRoomInfos
                .stream()
                .map { roomInfo ->
                    calculateDistance(currentCoordinate, roomInfo)
                }
                .sorted(
                        Comparator.comparing(
                                RealTimeEmergencyRoomWithDistance::distance
                        )
                )
                .toList()
    }

    fun calculateDistance(
            currentCoordinate: Coordinate,
            info: RealTimeEmergencyRoomInfo,
    ): RealTimeEmergencyRoomWithDistance {
        val emergencyRoom = emergencyRoomReader.read(info.hpId)
        val distance =
                currentCoordinate.distanceTo(emergencyRoom.getCoordinate())
        return RealTimeEmergencyRoomWithDistance.of(info, distance)
    }
}
