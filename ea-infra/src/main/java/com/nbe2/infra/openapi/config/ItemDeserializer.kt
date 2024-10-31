package com.nbe2.infra.openapi.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import com.nbe3.infra.openapi.dto.OpenApiResponse
import com.nbe3.infra.openapi.dto.OpenApiResponse.Response.Body.Items
import java.io.IOException

class ItemDeserializer : JsonDeserializer<Items<*>>, ContextualDeserializer {
    private var valueType: JavaType? = null

    constructor()
    private constructor(valueType: JavaType) {
        this.valueType = valueType
    }

    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Items<*> {
        val mapper = p.codec as ObjectMapper
        val node = mapper.readTree<JsonNode>(p)
        val items: MutableList<Any> = ArrayList()
        val itemNode = node["item"]
        return if (itemNode.isArray) {
            for (subItemNode in itemNode) {
                val contentType = valueType!!.contentType
                items.add(mapper.treeToValue(subItemNode, contentType))
            }
            Items(items)
        } else {
            val item = mapper.treeToValue<Any>(itemNode, valueType)
            Items(item)
        }
    }

    override fun createContextual(
        ctxt: DeserializationContext, property: BeanProperty
    ): JsonDeserializer<*> {
        val wrapperType = ctxt.contextualType
        if (wrapperType != null && wrapperType.containedTypeCount() > 0) {
            val itemType = wrapperType.containedType(0)
            return ItemDeserializer(itemType)
        }
        return this
    }
}
