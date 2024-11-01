package com.nbe2.domain.posts

import com.nbe2.domain.posts.exception.PostNotFoundException
import org.springframework.stereotype.Component

@Component
class PostDeleter(private val postRepository: PostRepository) {

    fun delete(postsId: Long) {
        val post =
                postRepository.findById(postsId).orElseThrow {
                    PostNotFoundException.EXCEPTION
                }!!
        postRepository.delete(post)
    }
}
