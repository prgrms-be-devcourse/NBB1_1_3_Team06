package com.nbe2.infra.openapi.config

import org.springframework.context.annotation.Bean

class OpenApiFeignClientConfiguration {
    @Bean
    fun openApiRequestInterceptor(): OpenApiRequestInterceptor {
        return OpenApiRequestInterceptor()
    }
}
