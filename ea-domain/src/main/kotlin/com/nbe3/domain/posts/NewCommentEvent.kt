package com.nbe3.domain.posts

@JvmRecord
data class NewCommentEvent(val targetId: Long, val referenceUri: String, val postTitle: String) {
    companion object {
        @JvmStatic
        fun from(post: Post): NewCommentEvent {
            return NewCommentEvent(
                post.user.id!!, "/posts/" + post.id, post.title
            )
        }
    }
}
