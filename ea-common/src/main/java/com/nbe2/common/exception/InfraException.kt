package com.nbe2.common.exception

open class InfraException : CustomException {
    constructor(errorCode: BaseErrorCode, message: String) : super(errorCode, message)

    constructor(errorCode: BaseErrorCode) : super(errorCode, "인프라 계층 예외")
}
