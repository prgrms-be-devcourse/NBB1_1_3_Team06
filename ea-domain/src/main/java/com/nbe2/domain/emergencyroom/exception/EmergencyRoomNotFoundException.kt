package com.nbe2.domain.emergencyroom.exception

import com.nbe2.common.exception.DomainException

object EmergencyRoomNotFoundException : DomainException(EmergencyRoomErrorCode.EMERGENCY_ROOM_NOT_FOUND_ERROR) {
    private fun readResolve(): Any = EmergencyRoomNotFoundException

}
