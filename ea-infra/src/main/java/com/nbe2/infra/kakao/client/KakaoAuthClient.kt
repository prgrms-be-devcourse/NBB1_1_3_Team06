package com.nbe2.infra.kakao.client

import com.nbe2.infra.feign.config.FeignConfig
import com.nbe2.infra.kakao.dto.KakaoTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "kakao-auth-client", url = "https://kauth.kakao.com", configuration = [FeignConfig::class])
interface KakaoAuthClient {
    @PostMapping("/oauth/token?client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&grant_type=authorization_code")
    fun auth(
        @PathVariable("CLIENT_ID") clientId: String,
        @PathVariable("REDIRECT_URI") redirectUri: String,
        @PathVariable("CODE") code: String
    ): KakaoTokenResponse
}
