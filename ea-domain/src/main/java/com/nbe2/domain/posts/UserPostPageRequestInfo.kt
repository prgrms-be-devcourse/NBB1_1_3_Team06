package com.nbe2.domain.posts

import com.nbe2.common.dto.Page

data class UserPostPageRequestInfo(val email: String, val page: Page) {
    companion object {
        fun create(email: String, page: Page): UserPostPageRequestInfo {
            return UserPostPageRequestInfo(email, page)
        }
    }
}
