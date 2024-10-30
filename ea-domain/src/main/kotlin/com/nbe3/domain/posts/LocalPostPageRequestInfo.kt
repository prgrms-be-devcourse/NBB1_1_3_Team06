package com.nbe3.domain.posts

import com.nbe3.common.dto.Page

class LocalPostPageRequestInfo(val city: City, page: Page) {
    val page: Page = page

    companion object {
        fun create(city: City, page: Page): LocalPostPageRequestInfo {
            return LocalPostPageRequestInfo(city, page)
        }
    }
}
