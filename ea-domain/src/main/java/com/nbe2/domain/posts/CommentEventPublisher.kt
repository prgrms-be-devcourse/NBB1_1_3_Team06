package com.nbe2.domain.posts

interface CommentEventPublisher {
    fun publish(event: NewCommentEvent)
}
