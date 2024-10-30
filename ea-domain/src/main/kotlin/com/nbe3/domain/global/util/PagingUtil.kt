package com.nbe3.domain.global.util

import com.nbe3.common.dto.Page
import org.springframework.data.domain.PageRequest

class PagingUtil {

    companion object {
        fun toPageRequest(page: Page): PageRequest {
            return PageRequest.of(page.page, page.size)
        }
    }
}
