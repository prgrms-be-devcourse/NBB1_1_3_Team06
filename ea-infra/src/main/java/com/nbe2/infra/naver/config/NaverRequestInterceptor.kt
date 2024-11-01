package com.nbe2.infra.naver.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class NaverRequestInterceptor : RequestInterceptor {
    @Value("\${naver.directions-client-id}")
    private val clientKey: String? = null

    @Value("\${naver.directions-secret-key}")
    private val serviceKey: String? = null

    override fun apply(template: RequestTemplate) {
        template.header("Accept", "application/json") // Accept 헤더 추가
        template.header("x-ncp-apigw-api-key-id", clientKey) // Accept 헤더 추가
        template.header("x-ncp-apigw-api-key", serviceKey) // Accept 헤더 추가
    }
}
