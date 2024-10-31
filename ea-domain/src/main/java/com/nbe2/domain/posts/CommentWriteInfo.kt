package com.nbe2.domain.posts

data class CommentWriteInfo(val userId: Long, val commentInfo: CommentInfo) {
    companion object {
        fun create(userId: Long, commentInfo: CommentInfo): CommentWriteInfo {
            return CommentWriteInfo(userId, commentInfo)
        }
    }
}
