package com.nbe2.domain.emergencyroom

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class RealTimeEmergencyRoomInfoFetcher(
    private val realTimeClient: RealTimeClient,
    private val coordinateConverter: CoordinateToRegionConverter,
    private val cacheManager: RealTimeEmergencyRoomInfoCacheManager,
    private val realTimeEmergencyRoomInfoCacheManager: RealTimeEmergencyRoomInfoCacheManager
) {

    fun fetch(currentCoordinate: Coordinate): List<RealTimeEmergencyRoomInfo> {
        val region = coordinateConverter.convert(currentCoordinate)
        val realTimeInfo = realTimeClient.getRealTimeInfo(region)
        cacheManager.cache(realTimeInfo)
        return realTimeInfo
    }

    // @TODO 기존의 fetch가 아니라 hpId로 캐싱해주는 로직이 필요함
    fun reloadRealTimeEmergencyRooms(
        currentCoordinate: Coordinate, hospitalId: String
    ): RealTimeEmergencyRoomInfo {
        fetch(currentCoordinate)
        return realTimeEmergencyRoomInfoCacheManager.getInfo(hospitalId).get()
    }
}
