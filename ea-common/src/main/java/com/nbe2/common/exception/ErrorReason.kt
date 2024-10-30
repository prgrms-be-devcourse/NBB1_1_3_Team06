package com.nbe2.common.exception

data class ErrorReason(val status: Int, val errorCode: String, val message: String) {

    companion object {
        @JvmStatic
        fun of(status: Int, errorCode: String, message: String): ErrorReason {
            return ErrorReason(status, errorCode, message)
        }
    }
}