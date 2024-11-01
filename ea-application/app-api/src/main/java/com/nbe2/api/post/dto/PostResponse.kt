package com.nbe2.api.post.dto

import com.nbe2.domain.posts.PostDetailsInfo

class PostResponse(val postDetailsInfo: PostDetailsInfo) {

    companion object {
        fun from(postDetailsInfo: PostDetailsInfo): PostResponse {
            return PostResponse(postDetailsInfo)
        }
    }
}
