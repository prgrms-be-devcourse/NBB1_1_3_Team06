package com.nbe2.common.dto

import java.util.function.Function

data class PageResult<T>(val content: List<T>, val totalPage: Int, val hasNext: Boolean) {

    fun <R> map(mapper: Function<T, R>?): PageResult<R> {
        val mappedContent = content.stream().map(mapper).toList()
        return of(mappedContent, totalPage, hasNext)
    }

    companion object {
        @JvmStatic
        fun <T> of(content: List<T>, totalPage: Int, hasNext: Boolean): PageResult<T> {
            return PageResult(content, totalPage, hasNext)
        }
    }
}
