package com.nbe3.api.global.config

import com.nbe3.common.annotation.CursorDefault
import com.nbe3.common.dto.Cursor
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class CursorArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(CursorDefault::class.java)
                && parameter.parameterType == Cursor::class.java
    }

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val cursorDefault = parameter.getParameterAnnotation(CursorDefault::class.java)!!

        val cursorParam = webRequest.getParameter("cursor")
        val cursor = cursorParam?.toLongOrNull() ?: cursorDefault.cursor

        val sizeParam = webRequest.getParameter("size")
        val size = sizeParam?.toIntOrNull() ?: cursorDefault.size

        return Cursor(cursor, size)
    }
}
