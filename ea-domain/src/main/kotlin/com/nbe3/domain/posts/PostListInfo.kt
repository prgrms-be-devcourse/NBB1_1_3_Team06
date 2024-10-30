package com.nbe3.domain.posts

@JvmRecord
data class PostListInfo(
    val id: Long,
    val name: String,
    val title: String,
    val content: String,
    val likeCount: Long,
    val commentCount: Long
)
