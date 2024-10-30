package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import org.springframework.stereotype.Component

@Component
class PostAppender (private val postFileRegisterer: PostFileRegisterer, private val postRepository: PostRepository){

    fun append(user: User?, info: PostWriteInfo): Long? {
        val newPost =
            postRepository.save(Post.create(user!!, info.title, info.content, info.city))
        postFileRegisterer.register(newPost, info.fileIdList)
        return newPost.id
    }
}
