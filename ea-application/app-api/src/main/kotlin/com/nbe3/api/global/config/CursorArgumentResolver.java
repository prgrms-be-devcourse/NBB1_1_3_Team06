package com.nbe3.api.global.config;

import com.nbe2.common.annotation.CursorDefault;
import com.nbe2.common.dto.Cursor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CursorArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CursorDefault.class)
                && parameter.getParameterType().equals(Cursor.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        CursorDefault cursorDefault = parameter.getParameterAnnotation(CursorDefault.class);

        String cursorParam = webRequest.getParameter("cursor");
        long cursor = (cursorParam == null) ? cursorDefault.cursor() : Long.parseLong(cursorParam);

        String sizeParam = webRequest.getParameter("size");
        int size = (sizeParam == null) ? cursorDefault.size() : Integer.parseInt(sizeParam);

        return new Cursor(cursor, size);
    }
}
