package com.nbe2.domain.posts.exception

import com.nbe2.common.exception.DomainException

class CommentWriterMismatchException
private constructor(errorCode: CommentErrorCode) : DomainException(errorCode) {
    companion object {
        val EXCEPTION: DomainException =
                CommentWriterMismatchException(
                        CommentErrorCode.COMMENT_WRITER_MISMATCH
                )
    }
}
// object CommentWriterMismatchException : DomainException(CommentErrorCode.COMMENT_WRITER_MISMATCH)
