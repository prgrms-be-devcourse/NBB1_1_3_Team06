package com.nbe3.api.global.exception

import com.nbe3.common.exception.GlobalErrorCode
import com.nbe3.common.exception.WebException

class FileNotFoundException private constructor(errorCode: GlobalErrorCode) : WebException(errorCode) {
    
    companion object {
        val EXCEPTION: WebException = FileNotFoundException(GlobalErrorCode.FILE_NOT_FOUND)
    }
}
