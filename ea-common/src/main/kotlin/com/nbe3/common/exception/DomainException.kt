package com.nbe3.common.exception

class DomainException : CustomException {
    constructor(errorCode: BaseErrorCode, message: String) : super(errorCode, message)

    constructor(errorCode: BaseErrorCode) : super(errorCode, "도메인 계층 예외")
}
