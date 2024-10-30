package com.nbe3.api.global.config

import com.nbe3.common.annotation.PageDefault
import com.nbe3.common.dto.Page
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class PageArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(PageDefault::class.java)
                && parameter.parameterType == Page::class.java
    }

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val pageDefault: PageDefault = parameter.getParameterAnnotation(PageDefault::class.java)!!

        val pageParam: String? = webRequest.getParameter("page")
        val page = pageParam?.toIntOrNull() ?: pageDefault.page

        val sizeParam: String? = webRequest.getParameter("size")
        val size = sizeParam?.toIntOrNull() ?: pageDefault.size

        return Page(page, size)
    }
}
