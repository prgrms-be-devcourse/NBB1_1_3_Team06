package com.nbe3.domain.user.exception

import com.nbe3.common.exception.DomainException

class HospitalRequiredException private constructor (errorCode: UserErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = HospitalRequiredException(UserErrorCode.HOSPITAL_REQUIRED)
    }
}
