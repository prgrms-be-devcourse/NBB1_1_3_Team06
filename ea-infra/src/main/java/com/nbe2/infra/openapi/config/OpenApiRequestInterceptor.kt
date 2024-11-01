package com.nbe2.infra.openapi.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class OpenApiRequestInterceptor : RequestInterceptor {
    @Value("\${openapi.service-key}")
    private val serviceKey: String? =
            null // application.yml이나 properties 파일에 설정된 API 키를 불러옵니다.

    override fun apply(template: RequestTemplate) {
        template.header("Accept", "application/json") // Accept 헤더 추가
        template.query("serviceKey", serviceKey) // serviceKey 파라미터 추가
    }
}
