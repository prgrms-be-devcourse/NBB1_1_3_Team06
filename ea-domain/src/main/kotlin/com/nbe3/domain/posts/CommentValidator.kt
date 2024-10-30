package com.nbe3.domain.posts

import com.nbe3.domain.posts.exception.CommentWriterMismatchException
import org.springframework.stereotype.Component

@Component
class CommentValidator {
    fun isOwnerId(userId: Long, comment: Comment?) {
        val commentWriterId = comment?.writerId
        if (userId != commentWriterId) throw CommentWriterMismatchException.EXCEPTION
    }
}
