package com.nbe2.infra.naver.config

import org.springframework.context.annotation.Bean

class NaverApiClientConfiguration {
    @Bean
    fun naverRequestInterceptor(): NaverRequestInterceptor {
        return NaverRequestInterceptor()
    }
}
