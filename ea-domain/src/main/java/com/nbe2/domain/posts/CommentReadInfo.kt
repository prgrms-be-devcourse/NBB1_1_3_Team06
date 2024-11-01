package com.nbe2.domain.posts

class CommentReadInfo(val name: String?, val info: CommentInfo) {

    companion object {
        fun from(comment: Comment?): CommentReadInfo {
            return CommentReadInfo(
                    comment?.writerName,
                    CommentInfo.of(
                            comment?.content ?: throw IllegalArgumentException()
                    ),
            )
        }
    }
}
