package com.nbe2.domain.user.exception

import com.nbe2.common.exception.DomainException

object AlreadyExistsEmailException : DomainException(UserErrorCode.ALREADY_EXISTS_EMAIL) {
    private fun readResolve(): Any = AlreadyExistsEmailException
}
