package com.nbe2.domain.user.exception

import com.nbe2.common.exception.DomainException

object InvalidPasswordException :
        DomainException(UserErrorCode.INVALID_PASSWORD) {
    private fun readResolve(): Any = InvalidPasswordException
}
