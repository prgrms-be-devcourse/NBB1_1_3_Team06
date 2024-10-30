package com.nbe3.domain.user.exception

import com.nbe3.common.exception.DomainException

class MedicalLicenseRequiredException private constructor (errorCode: UserErrorCode) : DomainException(errorCode) {

    companion object {
        val EXCEPTION: DomainException = MedicalLicenseRequiredException(UserErrorCode.MEDICAL_LICENSE_REQUIRED)
    }
}