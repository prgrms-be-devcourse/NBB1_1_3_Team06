package com.nbe2.domain.posts.exception

import com.nbe2.common.constants.EAConstants.NOT_FOUND
import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason
import com.nbe2.common.exception.ErrorReason.Companion.of
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
enum class PostErrorCode (
    private val status: Int,
    private val errorCode: String,
    private val message: String
) : BaseErrorCode {
    POST_NOT_FOUND(NOT_FOUND, "POST_404_1", "존재하지 않는 게시물입니다.");

    override val errorReason: ErrorReason
        get() = of(status, errorCode, message)
}
