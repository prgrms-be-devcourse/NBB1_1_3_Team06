package com.nbe2.domain.user.exception

import com.nbe2.common.exception.DomainException

object UserNotFoundException : DomainException(UserErrorCode.USER_NOT_FOUND) {
    private fun readResolve(): Any = UserNotFoundException
}
