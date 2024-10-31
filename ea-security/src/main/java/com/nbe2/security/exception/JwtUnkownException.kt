package com.nbe2.security.exception

import com.nbe2.common.exception.WebException

object JwtUnkownException : WebException(JwtErrorCode.UNKNOWN_EXCEPTION) {
    val EXCEPTION: WebException = JwtUnkownException
}
