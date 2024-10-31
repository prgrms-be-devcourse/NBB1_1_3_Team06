package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import org.springframework.stereotype.Component

@Component
class LikeValidator(private val likeRepository: LikeRepository) {

    fun isExist(post: Post?, user: User?): Boolean {
        return likeRepository.findByPostAndUser(post, user).isPresent
    }
}
