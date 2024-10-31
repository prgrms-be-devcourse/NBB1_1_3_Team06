package com.nbe2.infra.kakao.client

import com.nbe2.infra.feign.config.FeignConfig
import com.nbe2.infra.kakao.dto.KakaoTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakao-auth-client", url = "https://kauth.kakao.com", configuration = [FeignConfig::class])
interface KakaoAuthClient {

    @PostMapping("/oauth/token")
    fun auth(
        @RequestParam("client_id") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("code") code: String,
        @RequestParam("grant_type") grantType: String = "authorization_code",
    ): KakaoTokenResponse
}
