package com.nbe2.domain.auth.exception

import com.nbe2.common.exception.DomainException

object RefreshNotFoundException :
        DomainException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND) {
    private fun readResolve(): Any = RefreshNotFoundException
}
