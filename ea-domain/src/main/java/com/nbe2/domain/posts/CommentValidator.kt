package com.nbe2.domain.posts

import com.nbe2.domain.posts.exception.CommentWriterMismatchException
import org.springframework.stereotype.Component

@Component
class CommentValidator {
    fun isOwnerId(userId: Long, comment: Comment?) {
        val commentWriterId = comment?.writerId
        if (userId != commentWriterId)
                throw CommentWriterMismatchException.EXCEPTION
    }
}
