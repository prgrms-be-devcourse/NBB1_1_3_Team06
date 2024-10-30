package com.nbe3.domain.auth.exception

import com.nbe3.common.exception.DomainException

class AuthenticationException private constructor (errorCode: AuthErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = AuthenticationException(AuthErrorCode.AUTHENTICATION_FAILED)
    }
}
