package com.nbe3.domain.user.exception

import com.nbe3.common.exception.DomainException

class AlreadyExistsEmailException private constructor (errorCode: UserErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = AlreadyExistsEmailException(UserErrorCode.ALREADY_EXISTS_EMAIL)
    }
}
