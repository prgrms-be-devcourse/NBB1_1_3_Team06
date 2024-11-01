package com.nbe2.domain.emergencyroom

import java.util.*

interface RealTimeEmergencyRoomInfoCacheRepository {
    fun cache(info: RealTimeEmergencyRoomInfo)

    fun findById(hpId: String): Optional<RealTimeEmergencyRoomInfo>
}
