package com.nbe2.domain.posts

import com.nbe2.domain.posts.exception.CommentNotFoundException
import org.springframework.stereotype.Component

@Component
class CommentReader(private val commentRepository: CommentRepository) {

    fun read(commentId: Long): Comment {
        return commentRepository.findById(commentId).orElseThrow {
            CommentNotFoundException.EXCEPTION
        }!!
    }

    fun read(post: Post): List<Comment?>? {
        return commentRepository.findByPostId(post.id)
    }
}
