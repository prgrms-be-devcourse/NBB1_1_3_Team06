package com.nbe3.domain.user.exception

import com.nbe3.common.constants.EAConstants.BAD_REQUEST
import com.nbe3.common.constants.EAConstants.NOT_FOUND
import com.nbe3.common.exception.BaseErrorCode
import com.nbe3.common.exception.ErrorReason

enum class UserErrorCode(
    private val status: Int,
    private val errorCode: String,
    private val message: String) : BaseErrorCode
{
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "존재하지 않는 회원입니다."),
    ALREADY_EXISTS_EMAIL(BAD_REQUEST, "USER_400_1", "이미 존재하는 이메일입니다."),
    HOSPITAL_REQUIRED(BAD_REQUEST, "USER_400_2", "근무 중인 병원을 지정해야 합니다."),
    MEDICAL_LICENSE_REQUIRED(BAD_REQUEST, "USER_400_3", "의료 면허가 필요합니다."),
    INVALID_PASSWORD(BAD_REQUEST, "USER_400_4", "비밀번호가 일치하지 않습니다.");

    override val errorReason: ErrorReason
        get() = ErrorReason.of(status, errorCode, message)
}
