package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import org.springframework.stereotype.Component

@Component
class LikeDeleter (private val likeRepository: LikeRepository){


    fun delete(post: Post?, user: User?) {
        likeRepository.deleteByPostAndUser(post, user)
    }
}
