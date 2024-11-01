package com.nbe2.infra.feign.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.InfraException

object OtherServerUnauthorizedException : InfraException(GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED) {
    private fun readResolve(): Any = OtherServerUnauthorizedException
}
