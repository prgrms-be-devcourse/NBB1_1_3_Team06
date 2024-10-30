package com.nbe3.domain.auth.exception

import com.nbe3.common.exception.DomainException

class RefreshNotFoundException private constructor (errorCode: AuthErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: RefreshNotFoundException = RefreshNotFoundException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND)
    }
}
