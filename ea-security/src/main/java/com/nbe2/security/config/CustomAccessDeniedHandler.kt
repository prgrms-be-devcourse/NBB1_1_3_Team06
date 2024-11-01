package com.nbe2.security.config

import com.nbe2.security.exception.NoPermissionException
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class CustomAccessDeniedHandler(
        @Qualifier("handlerExceptionResolver")
        private val resolver: HandlerExceptionResolver
) : AccessDeniedHandler {

    @Throws(IOException::class, ServletException::class)
    override fun handle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            accessDeniedException: AccessDeniedException?,
    ) {
        resolver.resolveException(
                request,
                response,
                null,
                NoPermissionException.EXCEPTION,
        )
    }
}
