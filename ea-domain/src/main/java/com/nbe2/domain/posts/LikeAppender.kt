package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import org.springframework.stereotype.Component

@Component
class LikeAppender(private val likeRepository: LikeRepository) {

    fun append(post: Post, user: User) {
        likeRepository.save<Like>(Like.Companion.create(post, user))
    }
}
