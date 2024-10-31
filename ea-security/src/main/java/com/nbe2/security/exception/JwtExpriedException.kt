package com.nbe2.security.exception

import com.nbe2.common.exception.WebException

object JwtExpriedException : WebException(JwtErrorCode.TOKEN_EXPIRED) {
    val EXCEPTION: WebException = JwtExpriedException
}
