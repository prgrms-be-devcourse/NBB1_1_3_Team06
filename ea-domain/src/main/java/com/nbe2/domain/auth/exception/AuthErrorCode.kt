package com.nbe2.domain.auth.exception

import com.nbe2.common.constants.EAConstants.UNAUTHORIZED
import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason

enum class AuthErrorCode(
        private val status: Int,
        private val errorCode: String,
        private val message: String,
) : BaseErrorCode {
    AUTHENTICATION_FAILED(UNAUTHORIZED, "AUTH_401_1", "로그인에 실패했습니다."),
    REFRESH_TOKEN_NOT_FOUND(
            UNAUTHORIZED,
            "AUTH_401_2",
            "Refresh Token이 유효하지 않습니다. 다시 로그인 해주세요.",
    );

    override val errorReason: ErrorReason
        get() = ErrorReason.of(status, errorCode, message)
}
