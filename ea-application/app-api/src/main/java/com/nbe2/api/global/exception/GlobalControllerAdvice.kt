package com.nbe2.api.global.exception

import com.nbe2.api.global.dto.Response
import com.nbe2.common.exception.CustomException
import com.nbe2.common.exception.ErrorReason
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [CustomException::class])
    fun customError(
            e: CustomException,
            request: HttpServletRequest,
    ): ResponseEntity<*> =
            ResponseEntity.status(e.status)
                    .body(
                            Response.error(
                                    e.errorCode.errorReason,
                                    request.requestURI,
                                    e.message,
                            )
                    )

    @ExceptionHandler(value = [java.lang.Exception::class])
    fun error(e: Exception, request: HttpServletRequest): ResponseEntity<*> {
        log.error("error", e)
        return ResponseEntity.status(500)
                .body(
                        Response.error(
                                ErrorReason.of(
                                        500,
                                        "INTERNAL_SERVER_ERROR",
                                        "알수없는 서버 에러",
                                ),
                                request.requestURI,
                                e.message!!,
                        )
                )
    }
}
