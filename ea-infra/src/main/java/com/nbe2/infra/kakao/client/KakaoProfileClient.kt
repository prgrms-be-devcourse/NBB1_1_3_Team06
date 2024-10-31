package com.nbe2.infra.kakao.client

import com.nbe2.infra.feign.config.FeignConfig
import com.nbe2.infra.kakao.dto.KakaoProfileResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakao-profile-client", url = "https://kapi.kakao.com", configuration = [FeignConfig::class])
interface KakaoProfileClient {

    @GetMapping("/v1/oidc/userinfo")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): KakaoProfileResponse
}
