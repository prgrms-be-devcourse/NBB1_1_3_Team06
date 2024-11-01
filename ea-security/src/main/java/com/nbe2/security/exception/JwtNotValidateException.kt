package com.nbe2.security.exception

import io.jsonwebtoken.JwtException

object JwtNotValidateException :
        JwtException(JwtErrorCode.TOKEN_NOT_VALIDATE.toString()) {
    val EXCEPTION: JwtException = JwtException("기간이 유효하지 않습니다.")
}
