package com.nbe2.domain.emergencyroom.exception

import com.nbe2.common.exception.DomainException

object InvalidCoordinateException : DomainException(EmergencyRoomErrorCode.INVALID_COORDINATE) {
    private fun readResolve(): Any = InvalidCoordinateException
}
