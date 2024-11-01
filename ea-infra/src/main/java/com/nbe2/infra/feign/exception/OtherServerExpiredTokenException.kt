package com.nbe2.infra.feign.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.InfraException

object OtherServerExpiredTokenException :
        InfraException(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN) {
    private fun readResolve(): Any = OtherServerExpiredTokenException
}
