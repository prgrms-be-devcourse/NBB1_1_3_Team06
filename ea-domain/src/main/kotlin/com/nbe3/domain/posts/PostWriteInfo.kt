package com.nbe3.domain.posts

import java.util.*

// create, update 에 사용되는 정보
@JvmRecord
data class PostWriteInfo(@JvmField val title: String, @JvmField val content: String, @JvmField val city: City, @JvmField val fileIdList: Optional<List<Long>>) {
    companion object {
        fun create(
            title: String,
            content: String,
            city: City,
            fileIdList: Optional<List<Long>>
        ): PostWriteInfo {
            return PostWriteInfo(title, content, city, fileIdList)
        }
    }
}
