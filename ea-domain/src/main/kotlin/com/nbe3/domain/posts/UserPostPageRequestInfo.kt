package com.nbe3.domain.posts

import com.nbe3.common.dto.Page


@JvmRecord
data class UserPostPageRequestInfo(val email: String, val page: Page) {
    companion object {
        fun create(email: String, page: Page): UserPostPageRequestInfo {
            return UserPostPageRequestInfo(email, page)
        }
    }
}
