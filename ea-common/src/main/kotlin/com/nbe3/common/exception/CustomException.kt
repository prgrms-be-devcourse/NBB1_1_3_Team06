package com.nbe3.common.exception

open abstract class CustomException(errorCode: BaseErrorCode, sourceLayer: String?) : RuntimeException() {
    protected val errorCode: BaseErrorCode = errorCode
    protected val sourceLayer: String? = sourceLayer
>>>>>>> Stashed changes

    val status: Int
        get() = errorCode.errorReason.status

    override val message: String
        get() = sourceLayer?.let { "$it - ${errorCode.errorReason.message}" }
            ?: errorCode.errorReason.message
}
