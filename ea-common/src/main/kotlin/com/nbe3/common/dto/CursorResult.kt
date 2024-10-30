package com.nbe3.common.dto

import java.util.function.Function

data class CursorResult<T>(val content: List<T>, val nextCursor: Long) {
    fun <R> map(mapper: Function<T, R>?): CursorResult<R> {
        val mappedContent = content.stream().map(mapper).toList()
        return of(mappedContent, nextCursor)
    }

    companion object {
        fun <T> of(content: List<T>, nextCursor: Long): CursorResult<T> {
            return CursorResult(content, nextCursor)
        }
    }
}
