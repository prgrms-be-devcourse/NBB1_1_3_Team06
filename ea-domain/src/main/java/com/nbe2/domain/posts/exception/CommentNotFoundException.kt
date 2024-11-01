package com.nbe2.domain.posts.exception

import com.nbe2.common.exception.DomainException

class CommentNotFoundException
private constructor(errorCode: CommentErrorCode) : DomainException(errorCode) {
    companion object {
        val EXCEPTION: DomainException =
                com.nbe2.domain.posts.exception.CommentNotFoundException(
                        CommentErrorCode.COMMENT_NOT_FOUND
                )
    }
}
// object CommentNotFoundException : DomainException(CommentErrorCode.COMMENT_NOT_FOUND)
