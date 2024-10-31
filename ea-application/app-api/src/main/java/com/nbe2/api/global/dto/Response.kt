package com.nbe2.api.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.nbe2.common.exception.ErrorReason
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
    val path: String? = null,
    val responseCode: String,
    val message: String? = null,
    val result: T? = null,
    val timeStamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun <T> success(result: T): Response<T> = Response(responseCode = "SUCCESS", result = result)

        fun success(): Response<Unit> = Response(responseCode = "SUCCESS")

        fun success(message: String): Response<Unit> = Response(responseCode = "SUCCESS", message = message)

        fun error(errorReason: ErrorReason, path: String, message: String): Response<Unit> =
            Response(path = path, responseCode = errorReason.errorCode, message = message)

        fun error(errorCode: String, message: String): Response<Unit> =
            Response(responseCode = errorCode, message = message)
    }
}