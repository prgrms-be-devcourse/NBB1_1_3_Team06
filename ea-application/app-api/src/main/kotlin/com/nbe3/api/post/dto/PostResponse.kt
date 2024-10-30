package com.nbe3.api.post.dto

import com.nbe3.domain.posts.PostDetailsInfo

class PostResponse(val postDetailsInfo: PostDetailsInfo) {


    companion object {
        fun from(postDetailsInfo: PostDetailsInfo): PostResponse {
            return PostResponse(postDetailsInfo)
        }
    }
}
