package com.nbe3.domain.posts

import org.springframework.stereotype.Component

@Component
class CommentUpdater {
    fun update(comment: Comment?, info: CommentInfo): Long? {
        return comment!!.update(info.content)
    }
}
