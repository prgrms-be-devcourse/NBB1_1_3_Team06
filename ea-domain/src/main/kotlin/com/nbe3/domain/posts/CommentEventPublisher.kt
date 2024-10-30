package com.nbe3.domain.posts

import org.springframework.stereotype.Component

@Component
interface CommentEventPublisher {
    fun publish(event: NewCommentEvent?)
}
