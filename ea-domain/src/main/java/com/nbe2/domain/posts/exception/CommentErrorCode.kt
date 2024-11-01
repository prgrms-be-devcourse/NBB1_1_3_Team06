package com.nbe2.domain.posts.exception

import com.nbe2.common.constants.EAConstants.BAD_REQUEST
import com.nbe2.common.constants.EAConstants.NOT_FOUND
import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason
import com.nbe2.common.exception.ErrorReason.Companion.of
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
enum class CommentErrorCode(
        private val status: Int,
        private val errorCode: String,
        private val message: String,
) : BaseErrorCode {

    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404_1", "존재하지 않는 댓글입니다."),
    COMMENT_WRITER_MISMATCH(BAD_REQUEST, "COMMENT_400_1", "댓글 작성자가 일치하지 않습니다.");

    override val errorReason: ErrorReason
        get() = of(status, errorCode, message)
}
