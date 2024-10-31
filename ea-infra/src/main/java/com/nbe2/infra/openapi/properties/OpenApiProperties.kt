package com.nbe2.infra.openapi.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OpenApiProperties {
    @Value("\${openapi.service-key}")
    fun setServiceKey(serviceKey: String?) {
        SERVICE_KEY = serviceKey
    }

    companion object {
        var SERVICE_KEY: String? = null
        var NUM_OF_ROWS = 10
    }
}
