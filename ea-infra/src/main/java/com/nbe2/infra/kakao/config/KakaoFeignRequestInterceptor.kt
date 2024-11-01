package com.nbe2.infra.kakao.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class KakaoFeignRequestInterceptor : RequestInterceptor {
    @Value("\${kakao.rest-api-key}") private val restApiKey: String? = null

    override fun apply(template: RequestTemplate) {
        template.header("Authorization", "KakaoAK $restApiKey")
    }
}
