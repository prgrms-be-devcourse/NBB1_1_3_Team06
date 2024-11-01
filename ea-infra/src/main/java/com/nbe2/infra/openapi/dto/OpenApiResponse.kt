package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.nbe2.infra.openapi.config.ItemDeserializer

data class OpenApiResponse<T>(
    val response: Response<T>
) {
    data class Response<T>(
        val header: Header,
        val body: Body<T>
    ) {
        data class Header(
            val resultCode: String,
            val resultMessage: String?
        )

        data class Body<T>(
            @JsonDeserialize(using = ItemDeserializer::class) val items: Items<T>,
            val numOfRows: Int,
            val pageNo: Int,
            val totalCount: Int
        ) {
            data class Items<T>(
                val item: T?
            )
        }
    }

    val item: T?
        get() = response.body.items.item
}