package com.nbe2.domain.posts

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PostFilePk(
    val fileMetaDataId: Long = 0L,
    val postId: Long = 0L
) : Serializable {
    companion object {
        fun of(fileMetaDataId: Long, postId: Long) = PostFilePk(fileMetaDataId, postId)
    }
}
