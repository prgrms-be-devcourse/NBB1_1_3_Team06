package com.nbe2.infra.emergencyroom.cache

import com.nbe2.common.logger
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfoCacheRepository
import com.nbe2.infra.emergencyroom.config.EmergencyRoomRedisConfig
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
@RequiredArgsConstructor
@Slf4j
class RedisRealTimeEmergencyRoomInfoCacheRepository

    : RealTimeEmergencyRoomInfoCacheRepository {

    private val template: RedisTemplate<String, RealTimeEmergencyRoomInfo>? = null
    private val log = logger()

    override fun cache(info: RealTimeEmergencyRoomInfo) {
        template!!.opsForValue()[getKey(info.hospitalId), info] = EmergencyRoomRedisConfig.REAL_TIME_INFO_TTL
    }

    override fun findById(hpId: String): Optional<RealTimeEmergencyRoomInfo> {
        val key = getKey(hpId)
        val realTimeInfo =
            Optional.ofNullable(template!!.opsForValue()[key])
        realTimeInfo.ifPresentOrElse(
            { info: RealTimeEmergencyRoomInfo ->
                log.info(
                    "Get User from Cache - {} : {}",
                    key,
                    info.hospitalName
                )
            },
            { log.info("No User Cache - {}", key) })
        return realTimeInfo
    }

    private fun getKey(hpId: String): String {
        return "REAL_TIME_ER:$hpId"
    }
}
