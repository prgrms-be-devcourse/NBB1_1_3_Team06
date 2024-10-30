package com.nbe3.domain.user.exception

import com.nbe3.common.exception.DomainException

class InvalidPasswordException private constructor (errorCode: UserErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = InvalidPasswordException(UserErrorCode.INVALID_PASSWORD)
    }
}