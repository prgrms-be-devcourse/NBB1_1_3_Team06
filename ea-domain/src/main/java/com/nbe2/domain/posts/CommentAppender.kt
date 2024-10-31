package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import org.springframework.stereotype.Component

@Component
class CommentAppender(private val commentRepository: CommentRepository) {


    fun append(post: Post, user: User, info: CommentInfo): Long {
        val comment: Comment = Comment.Companion.create(post, user, info.content)
        val saveComment = commentRepository.save(comment)
        return saveComment.id?: throw IllegalArgumentException()
    }
}
