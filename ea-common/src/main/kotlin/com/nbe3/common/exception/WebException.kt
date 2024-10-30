package com.nbe3.common.exception


class WebException(errorCode: BaseErrorCode) : CustomException(errorCode, "웹 계층 예외")
