package com.nbe3.domain.posts

import com.nbe3.domain.posts.exception.PostNotFoundException
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class PostDeleter (private val postRepository: PostRepository){

    fun delete(postsId: Long) {
        val post =
            postRepository.findById(postsId)
                .orElseThrow<RuntimeException>(Supplier<RuntimeException> { PostNotFoundException.EXCEPTION })!!
        postRepository.delete(post)
    }
}
