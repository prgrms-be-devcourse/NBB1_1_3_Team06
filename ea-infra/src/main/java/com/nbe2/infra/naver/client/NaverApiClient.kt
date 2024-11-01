package com.nbe2.infra.naver.client

import com.nbe2.infra.naver.config.NaverApiClientConfiguration
import com.nbe2.infra.naver.dto.NaverDirectionsResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "naverApiClient",
    url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/",
    configuration = [NaverApiClientConfiguration::class]
)
interface NaverApiClient {
    @GetMapping(value = ["/driving"])
    fun getEmergencyDirectionsData(
        @RequestParam goal: String, @RequestParam start: String, @RequestParam trafast: String
    ): NaverDirectionsResponse
}
