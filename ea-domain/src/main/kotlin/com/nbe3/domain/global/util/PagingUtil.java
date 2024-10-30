package com.nbe3.domain.global.util;

import com.nbe3.common.dto.Page;
import org.springframework.data.domain.PageRequest;

public class PagingUtil {

    public static PageRequest toPageRequest(Page page) {
        return PageRequest.of(page.getPage(), page.getSize());
    }
}
