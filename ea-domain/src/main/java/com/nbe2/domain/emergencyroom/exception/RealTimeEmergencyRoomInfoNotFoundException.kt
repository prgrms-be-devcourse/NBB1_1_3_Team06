package com.nbe2.domain.emergencyroom.exception

import com.nbe2.common.exception.InfraException

object RealTimeEmergencyRoomInfoNotFoundException :
        InfraException(
                EmergencyRoomErrorCode
                        .REAL_TIME_EMERGENCY_ROOM_INFO_NOT_FOUND_ERROR
        ) {
    private fun readResolve(): Any = RealTimeEmergencyRoomInfoNotFoundException
}
