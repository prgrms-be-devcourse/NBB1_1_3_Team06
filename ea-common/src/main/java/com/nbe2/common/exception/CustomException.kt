package com.nbe2.common.exception

abstract class CustomException(val errorCode: BaseErrorCode, private val sourceLayer: String?) : RuntimeException() {

    val status: Int
        get() = errorCode.errorReason.status

    override val message: String
        get() = sourceLayer?.let { "$it - ${errorCode.errorReason.message}" }
            ?: errorCode.errorReason.message
}
