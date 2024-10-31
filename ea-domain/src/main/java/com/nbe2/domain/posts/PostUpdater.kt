package com.nbe2.domain.posts

import org.springframework.stereotype.Component

@Component
class PostUpdater (private val postFileRegisterer: PostFileRegisterer){
    fun update(post: Post, info: PostWriteInfo): Long? {
        postFileRegisterer.register(post, info.fileIdList)
        val updated = post.update(info.title, info.content, info.city)
        return updated.id
    }
}
