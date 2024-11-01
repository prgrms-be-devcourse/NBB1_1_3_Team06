package com.nbe2.infra.kakao.config

import org.springframework.context.annotation.Bean

class KakaoFeignConfig {
    @Bean
    fun kakaoFeignRequestInterceptor(): KakaoFeignRequestInterceptor {
        return KakaoFeignRequestInterceptor()
    }
}
