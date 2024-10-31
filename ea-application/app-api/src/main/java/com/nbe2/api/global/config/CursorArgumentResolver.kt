package com.nbe2.api.global.config

import com.nbe2.common.annotation.CursorDefault
import com.nbe2.common.dto.Cursor
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class CursorArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter) =
        parameter.hasParameterAnnotation(CursorDefault::class.java)
                && parameter.parameterType == Cursor::class.java

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Cursor {
        val cursorDefault = parameter.getParameterAnnotation(CursorDefault::class.java)!!

        val cursorParam = webRequest.getParameter("cursor")
        val cursor = cursorParam?.toLongOrNull() ?: cursorDefault.cursor

        val sizeParam = webRequest.getParameter("size")
        val size = sizeParam?.toIntOrNull() ?: cursorDefault.size

        return Cursor(cursor, size)
    }
}
