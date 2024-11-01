package com.nbe2.infra.kakao.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class KakaoRequestInterceptor(
    @Value("\${kakao.rest-api-key}")
    private val restApiKey: String
) : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header("Authorization", "KakaoAK $restApiKey") // Authorization 헤더 추가
    }
}
