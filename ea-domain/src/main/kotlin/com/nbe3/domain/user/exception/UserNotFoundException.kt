package com.nbe3.domain.user.exception

import com.nbe3.common.exception.DomainException

class UserNotFoundException private constructor (errorCode: UserErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = UserNotFoundException(UserErrorCode.USER_NOT_FOUND)
    }
}