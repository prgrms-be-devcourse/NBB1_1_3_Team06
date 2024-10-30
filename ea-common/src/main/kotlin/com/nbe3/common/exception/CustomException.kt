package com.nbe3.common.exception

abstract class CustomException(errorCode: BaseErrorCode, sourceLayer: String?) : RuntimeException() {
    val errorCode: BaseErrorCode = errorCode
    val sourceLayer: String? = sourceLayer

    val status: Int
        get() = errorCode.errorReason.status

    override val message: String
        get() = errorCode.errorReason.message

//            if (sourceLayer == null) {
//            errorCode.getErrorReason().message
//        } else {
//            String.format("%s - %s", sourceLayer, errorCode.getErrorReason().message)
//        }
}
