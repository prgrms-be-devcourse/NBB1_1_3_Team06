package com.nbe2.domain.posts

data class NewCommentEvent(
        val targetId: Long,
        val referenceUri: String,
        val postTitle: String,
) {
    companion object {
        fun from(post: Post): NewCommentEvent {
            return NewCommentEvent(
                    post.user.id!!,
                    "/posts/${post.id}",
                    post.title,
            )
        }
    }
}
