package com.nbe2.common.exception

import com.nbe2.common.constants.EAConstants.BAD_REQUEST
import com.nbe2.common.constants.EAConstants.FORBIDDEN
import com.nbe2.common.constants.EAConstants.UNAUTHORIZED

enum class GlobalErrorCode(private val status: Int, private val errorCode: String, private val message: String) : BaseErrorCode {
    PERMISSION_DENIED(403, "PERMISSION_DENIED_401", "해당 API 권한이 없습니다"),
    FILE_NOT_FOUND(404, "FILE_NOT_FOUND_404", "해당 파일이 존재하지 않습니다"),
    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "AUTH_OTHER_400", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED(UNAUTHORIZED, "AUTH_OTHER_401", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN(FORBIDDEN, "AUTH_OTHER_403", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "AUTH_OTHER_400", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "AUTH_OTHER_400", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR(
        BAD_REQUEST, "FEIGN_400_6", "Other server internal server error"
    );

    constructor() : this(0, "", "")

    override val errorReason: ErrorReason
        get() = ErrorReason.of(status, errorCode, message)
}