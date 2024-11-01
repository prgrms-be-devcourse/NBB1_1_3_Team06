package com.nbe2.infra.kakao.client

import com.nbe2.infra.kakao.config.KakaoFeignConfig
import com.nbe2.infra.kakao.dto.KakaoApiResponse
import com.nbe2.infra.kakao.dto.KakaoRegionResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
        name = "kakao-client",
        url = "https://dapi.kakao.com",
        configuration = [KakaoFeignConfig::class],
)
interface KakaoClient {
    @GetMapping("/v2/local/geo/coord2regioncode.json")
    fun getRegionData(
            @RequestParam x: Double,
            @RequestParam y: Double,
    ): KakaoApiResponse<KakaoRegionResponse>
}
