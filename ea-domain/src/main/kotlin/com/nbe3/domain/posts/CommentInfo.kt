package com.nbe3.domain.posts

class CommentInfo(val content: String) {

    companion object {
        fun of(content: String): CommentInfo {
            return CommentInfo(content)
        }
    }
}
