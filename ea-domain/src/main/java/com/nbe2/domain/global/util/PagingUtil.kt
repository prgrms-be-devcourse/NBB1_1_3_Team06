package com.nbe2.domain.global.util

import com.nbe2.common.dto.Page
import org.springframework.data.domain.PageRequest

class PagingUtil {

    companion object {
        fun toPageRequest(page: Page) = PageRequest.of(page.page, page.size)
    }
}
