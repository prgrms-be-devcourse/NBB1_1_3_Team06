package com.nbe2.domain.emergencyroom

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmergencyRoomService(
    private val coordinateConverter: CoordinateToRegionConverter,
    private val realTimeInfoFetcher: RealTimeEmergencyRoomInfoFetcher,
    private val distanceCalculator: DistanceCalculator,
    private val emergencyRoomReader: EmergencyRoomReader,
    private val emergencyRoomDirections: EmergencyRoomDirections,
    private val realTimeEmergencyRoomInfoCacheManager: RealTimeEmergencyRoomInfoCacheManager,
    private val emergencyRoomInitializer: EmergencyRoomInitializer
) {
    @Transactional
    fun init() {
        emergencyRoomInitializer.init()
    }

    fun getRealTimeEmergencyRooms(
        currentCoordinate: Coordinate
    ): List<RealTimeEmergencyRoomWithDistance> {
        val realTimeInfos: List<RealTimeEmergencyRoomInfo> = realTimeInfoFetcher.fetch(currentCoordinate)
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate)
    }

    fun getEmergencyRoomListForName(name: String): List<String> {
        return emergencyRoomReader.readByHospitalName(name)
    }

    fun directionsEmergencyRoom(
        myLocation: String, hospitalName: String
    ): EmergencyRoomDirectionsInfo {
        val byHospitalLocation: Coordinate = emergencyRoomReader.readCoordinate(hospitalName)
        val latitudeAndLongitude: String = byHospitalLocation.convertorLatitudeAndLongitude()
        return emergencyRoomDirections.directionsEmergencyRoom(myLocation, latitudeAndLongitude)
    }

    fun getEmergencyRooms(coordinate: Coordinate, distance: Double): List<EmergencyRoomMapInfo> {
        return emergencyRoomReader.read(coordinate, distance)
    }

    fun getEmergencyRoomDetail(
        hospitalId: String, coordinate: Coordinate
    ): EmergencyRoomDetailInfo {
        val emergencyRoom: EmergencyRoom = emergencyRoomReader.read(hospitalId)
        val realTimeEmergencyRoomInfo: RealTimeEmergencyRoomInfo = realTimeEmergencyRoomInfoCacheManager
            .getInfo(emergencyRoom.hpId)
            .orElseGet {
                realTimeInfoFetcher.reloadRealTimeEmergencyRooms(
                    coordinate, hospitalId
                )
            }
        val realTimeEmergencyRoomWithDistance: RealTimeEmergencyRoomWithDistance =
            distanceCalculator.calculateDistance(coordinate, realTimeEmergencyRoomInfo)
        return EmergencyRoomDetailInfo.create(emergencyRoom, realTimeEmergencyRoomWithDistance)
    }
}
