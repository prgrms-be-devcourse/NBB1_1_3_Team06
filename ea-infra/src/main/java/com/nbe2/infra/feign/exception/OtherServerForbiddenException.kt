package com.nbe2.infra.feign.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.InfraException

object OtherServerForbiddenException : InfraException(GlobalErrorCode.OTHER_SERVER_FORBIDDEN) {
    private fun readResolve(): Any = OtherServerForbiddenException
}
