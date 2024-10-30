package com.nbe3.api.post.dto

import com.nbe3.domain.posts.CommentReadInfo

data class CommentResponse(val commentReadInfo: CommentReadInfo) {

    companion object {
        fun from(commentReadInfo: CommentReadInfo): CommentResponse {
            return CommentResponse(commentReadInfo)
        }
    }
}
