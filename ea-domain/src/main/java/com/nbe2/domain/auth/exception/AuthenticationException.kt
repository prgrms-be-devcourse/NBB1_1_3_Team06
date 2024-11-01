package com.nbe2.domain.auth.exception

import com.nbe2.common.exception.DomainException

object AuthenticationException :
        DomainException(AuthErrorCode.AUTHENTICATION_FAILED) {
    private fun readResolve(): Any = AuthenticationException
}
