package com.nbe3.domain.posts

interface CommentEventPublisher {
    fun publish(event: NewCommentEvent?)
}
