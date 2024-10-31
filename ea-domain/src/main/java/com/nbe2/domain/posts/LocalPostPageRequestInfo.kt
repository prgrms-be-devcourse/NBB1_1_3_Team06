package com.nbe2.domain.posts

import com.nbe2.common.dto.Page

class LocalPostPageRequestInfo(val city: City, val page: Page) {

    companion object {
        fun create(city: City, page: Page): LocalPostPageRequestInfo {
            return LocalPostPageRequestInfo(city, page)
        }
    }
}
