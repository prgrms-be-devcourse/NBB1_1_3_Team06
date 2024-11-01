package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.exception.EmergencyRoomNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class EmergencyRoomReader(
    private val emergencyRoomRepository: EmergencyRoomRepository
) {

    fun read(id: Long): EmergencyRoom {
        return emergencyRoomRepository
            .findById(id)
            .orElseThrow { EmergencyRoomNotFoundException }
    }

    fun read(hpId: String): EmergencyRoom {
        return emergencyRoomRepository
            .findByHpId(hpId)
            .orElseThrow { EmergencyRoomNotFoundException }
    }

    fun readByHospitalName(hospitalName: String): List<String> {
        return emergencyRoomRepository!!.findByHospitalNameContaining(hospitalName).stream()
            .map(EmergencyRoom::hpId)
            .toList()
    }

    fun readCoordinate(hospitalName: String): Coordinate {
        return emergencyRoomRepository
            .findByHospitalName(hospitalName)
            .map { obj: EmergencyRoom -> obj.getCoordinate() }
            .orElseThrow { EmergencyRoomNotFoundException }
    }

    fun read(coordinate: Coordinate, distance: Double): List<EmergencyRoomMapInfo> {
        return emergencyRoomRepository.findByCoordinateAndDistance(coordinate, distance)
    }
}
