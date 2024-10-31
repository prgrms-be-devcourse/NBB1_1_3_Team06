package com.nbe2.api.post.dto

import com.nbe2.domain.posts.CommentReadInfo

data class CommentResponse(val commentReadInfo: CommentReadInfo) {

    companion object {
        fun from(commentReadInfo: CommentReadInfo): CommentResponse {
            return CommentResponse(commentReadInfo)
        }
    }
}
