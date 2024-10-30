package com.nbe3.domain.posts.exception;

import com.nbe3.common.exception.BaseErrorCode;
import com.nbe3.common.exception.ErrorReason;
import org.jetbrains.annotations.NotNull;

import static com.nbe3.common.constants.EAConstants.*;

public enum CommentErrorCode implements BaseErrorCode {
    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404_1", "존재하지 않는 댓글입니다."),
    COMMENT_WRITER_MISMATCH(BAD_REQUEST, "COMMENT_400_1", "댓글 작성자가 일치하지 않습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    CommentErrorCode(Integer status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    @NotNull
    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.Companion.of(status, errorCode, message);
    }
}
