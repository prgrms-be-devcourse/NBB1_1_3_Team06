package com.nbe2.infra.emergencyroom.exception

import com.nbe2.common.exception.InfraException
import com.nbe2.domain.emergencyroom.exception.EmergencyRoomErrorCode

object RegionNotFoundException : InfraException(EmergencyRoomErrorCode.REGION_NOT_FOUND_ERROR) {
    private fun readResolve(): Any = RegionNotFoundException
}
