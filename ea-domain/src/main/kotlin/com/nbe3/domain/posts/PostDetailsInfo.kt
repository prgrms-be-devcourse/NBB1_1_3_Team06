package com.nbe3.domain.posts

import com.nbe3.domain.posts.PostFile

@JvmRecord
data class PostDetailsInfo(
    val id: Long?,
    val name: String,
    val title: String,
    val content: String,
    val likeCount: Long,
    val commentCount: Long,
    val fileIds: List<Long>
) {
    companion object {
        fun from(post: Post): PostDetailsInfo {
            return PostDetailsInfo(
                post.id,
                post.writerName,
                post.title,
                post.content,
                post.likeCount,
                post.commentCount,
                post.postFiles.stream().map(PostFile::fileMetaDataId).toList()
            )
        }
    }
}
