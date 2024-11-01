package com.nbe2.security.config

import com.nbe2.security.exception.JwtUnkownException
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class JwtAuthenticationEntryPoint(
        @Qualifier("handlerExceptionResolver")
        private val resolver: HandlerExceptionResolver
) : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException:
                    org.springframework.security.core.AuthenticationException?,
    ) {
        println("JwtAuthenticationEntryPoint commence")
        if (isExceptionInSecurityFilter(request)) {
            resolver.resolveException(
                    request,
                    response,
                    null,
                    request.getAttribute("exception") as Exception,
            )
        } else {
            resolver.resolveException(
                    request,
                    response,
                    null,
                    JwtUnkownException.EXCEPTION,
            )
        }
    }

    private fun isExceptionInSecurityFilter(
            request: HttpServletRequest
    ): Boolean {
        return request.getAttribute("exception") != null
    }
}
