package com.nbe2.domain.posts

import java.util.*

// create, update 에 사용되는 정보
data class PostWriteInfo(
        val title: String,
        val content: String,
        val city: City,
        val fileIdList: Optional<List<Long?>>,
) {
    companion object {
        fun create(
                title: String,
                content: String,
                city: City,
                fileIdList: Optional<List<Long?>>,
        ): PostWriteInfo {
            return PostWriteInfo(title, content, city, fileIdList)
        }
    }
}
