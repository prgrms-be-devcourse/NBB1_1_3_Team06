package com.nbe3.domain.posts

import com.nbe3.domain.posts.exception.CommentNotFoundException
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class CommentReader(
    private val commentRepository: CommentRepository
) {

    fun read(commentId: Long): Comment {
        return commentRepository
            .findById(commentId)
            .orElseThrow<RuntimeException>(Supplier<RuntimeException> { CommentNotFoundException.EXCEPTION })!!
    }

    fun read(post: Post): List<Comment?>? {
        return commentRepository.findByPostId(post.id)
    }
}
