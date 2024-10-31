package com.nbe2.api.global.exception

import com.nbe2.common.exception.GlobalErrorCode
import com.nbe2.common.exception.WebException

object FileNotFoundException : WebException(GlobalErrorCode.FILE_NOT_FOUND) {
    private fun readResolve(): Any = FileNotFoundException
}
