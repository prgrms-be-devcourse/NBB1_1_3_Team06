package com.nbe2.domain.emergencyroom

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer

@Component
@RequiredArgsConstructor
class RealTimeEmergencyRoomInfoCacheManager(
    private val cacheRepository: RealTimeEmergencyRoomInfoCacheRepository
) {

    fun cache(info: List<RealTimeEmergencyRoomInfo>) {
        info.forEach { cache(it) }
    }

    fun cache(info: RealTimeEmergencyRoomInfo) {
        cacheRepository.cache(info)
    }

    fun getInfo(hpId: String): Optional<RealTimeEmergencyRoomInfo> {
        return cacheRepository.findById(hpId)
    }
}
