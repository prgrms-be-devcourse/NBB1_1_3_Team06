package com.nbe3.security.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.WebException

class NoPermissionException private constructor() : WebException(GlobalErrorCode.PERMISSION_DENIED) {

    companion object {
        val EXCEPTION = NoPermissionException()
    }
}
