package com.nbe2.api.global.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.WebException

class FileNotFoundException private constructor(errorCode: GlobalErrorCode) : WebException(errorCode) {

    companion object {
        val EXCEPTION = FileNotFoundException(GlobalErrorCode.FILE_NOT_FOUND)
    }
}
