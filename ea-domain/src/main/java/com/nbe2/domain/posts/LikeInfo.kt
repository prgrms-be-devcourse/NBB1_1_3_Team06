package com.nbe2.domain.posts

class LikeInfo(val postId: Long, val userId: Long) {

    companion object {
        fun of(postId: Long, userId: Long): LikeInfo {
            return LikeInfo(postId, userId)
        }
    }
}
