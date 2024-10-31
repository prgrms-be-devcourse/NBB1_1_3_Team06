package com.nbe2.api.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.nbe2.common.exception.ErrorReason
import lombok.Getter
import java.time.LocalDateTime

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class Response<T> {
    private var path: String? = null
    private var responseCode: String
    private var message: String? = null
    private var result: T? = null
    private var timeStamp: LocalDateTime? = null

    private constructor(responseCode: String, result: T) {
        this.responseCode = responseCode
        this.result = result
    }

    private constructor(responseCode: String, message: String?) {
        this.responseCode = responseCode
        this.message = message
    }

    constructor(path: String?, errorCode: String, message: String?) {
        this.path = path
        responseCode = errorCode
        this.message = message
        timeStamp = LocalDateTime.now()
    }

    companion object {
        fun <T> success(result: T): Response<T> {
            return Response("SUCCESS", result)
        }

        fun success(): Response<Void> {
            return Response("SUCCESS", null)
        }

        fun success(message: String?): Response<Void> {
            return Response("SUCCESS", message)
        }

        fun error(errorReason: ErrorReason, path: String?, message: String?): Response<Void> {
            return Response(path, errorReason.errorCode, message)
        }

        fun error(errorCode: String, message: String?): Response<Void> {
            return Response(errorCode, message)
        }
    }
}
