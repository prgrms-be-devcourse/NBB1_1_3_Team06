package com.nbe2.api.notification.sse.exception

import com.nbe2.common.constants.EAConstants.INTERNAL_SERVER
import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason

enum class SseErrorCode(
    private val status: Int,
    private val errorCode: String,
    private val message: String
) : BaseErrorCode {
    SSE_CONNECTION_FAILED(INTERNAL_SERVER, "SSE_500_1", "SSE 연결에 실패했습니다.");

    override val errorReason: ErrorReason
        get() = ErrorReason.of(status, errorCode, message)
}
