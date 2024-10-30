package com.nbe3.domain.posts

class CommentReadInfo(val name: String?, val info: CommentInfo) {

    companion object {
        fun from(comment: Comment?): CommentReadInfo {
            return CommentReadInfo(
                comment?.writerName,
                CommentInfo.Companion.of(comment?.content ?: throw IllegalArgumentException())
            )
        }
    }
}
