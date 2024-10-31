package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.nbe2.infra.openapi.config.ItemDeserializer

@JvmRecord
data class OpenApiResponse<T>(
    @field:JsonProperty("response") @param:JsonProperty("response") val response: Response<T>
) {
    data class Response<T>(
        @JsonProperty("header") val header: Header,
        @JsonProperty("body") val body: Body<T>
    ) {
        data class Header(
            @JsonProperty("resultCode") val resultCode: String,
            @JsonProperty("resultMsg") val resultMessage: String
        )

        data class Body<T>(
            @JsonProperty("items") @JsonDeserialize(using = ItemDeserializer::class) val items: Items<T>,
            @JsonProperty("numOfRows") val numOfRows: Int,
            @JsonProperty("pageNo") val pageNo: Int,
            @JsonProperty("totalCount") val totalCount: Int
        ) {
            data class Items<T>(
                @JsonProperty("item") val item: T
            )
        }
    }

    val items: T
        get() = response.body.items.item
}
