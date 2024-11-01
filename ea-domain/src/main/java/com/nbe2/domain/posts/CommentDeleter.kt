package com.nbe2.domain.posts

import org.springframework.stereotype.Component

@Component
class CommentDeleter(private val commentRepository: CommentRepository) {

    fun delete(comment: Comment?) {
        commentRepository.delete(comment!!)
    }
}
