package com.nbe3.domain.posts.exception;

import com.nbe3.common.exception.BaseErrorCode;
import com.nbe3.common.exception.ErrorReason;


public enum PostErrorCode implements BaseErrorCode {
    POST_NOT_FOUND(404, "POST_404_1", "존재하지 않는 게시물입니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    // Enum 생성자
    PostErrorCode(Integer status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.Companion.of(status, errorCode, message);
    }
}
