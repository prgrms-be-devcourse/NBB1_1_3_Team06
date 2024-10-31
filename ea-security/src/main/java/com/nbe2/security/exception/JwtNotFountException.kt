package com.nbe2.security.exception

import com.nbe2.common.exception.WebException


object JwtNotFountException : WebException(JwtErrorCode.ACCESS_TOKEN_NOT_FOUNT) {
    val EXCEPTION: WebException = JwtNotFountException
}
