package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import org.springframework.stereotype.Component

@Component
class LikeDeleter (private val likeRepository: LikeRepository){


    fun delete(post: Post?, user: User?) {
        likeRepository!!.deleteByPostAndUser(post, user)
    }
}
