package com.nbe2.api.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.nbe2.common.exception.ErrorReason
import java.time.LocalDateTime

@JvmRecord
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
    val path: String? = null,
    val responseCode: String,
    val message: String? = null,
    val result: T? = null,
    val timeStamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        @JvmStatic
        fun <T> success(result: T): Response<T> {
            return Response(responseCode = "SUCCESS", result = result)
        }

        @JvmStatic
        fun success(): Response<Unit> {
            return Response(responseCode = "SUCCESS")
        }

        @JvmStatic
        fun success(message: String): Response<Unit> {
            return Response(responseCode = "SUCCESS", message = message)
        }

        @JvmStatic
        fun error(errorReason: ErrorReason, path: String, message: String): Response<Unit> {
            return Response(path = path, responseCode = errorReason.errorCode, message = message)
        }

        @JvmStatic
        fun error(errorCode: String, message: String): Response<Unit> {
            return Response(responseCode = errorCode, message = message)
        }
    }
}