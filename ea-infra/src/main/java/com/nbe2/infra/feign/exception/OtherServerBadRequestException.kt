package com.nbe2.infra.feign.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.InfraException

object OtherServerBadRequestException : InfraException(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST) {
    private fun readResolve(): Any = OtherServerBadRequestException
}
