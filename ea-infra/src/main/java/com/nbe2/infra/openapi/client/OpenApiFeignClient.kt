package com.nbe2.infra.openapi.client

import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse
import com.nbe2.infra.openapi.config.OpenApiFeignClientConfiguration
import com.nbe2.infra.openapi.dto.AllEmergencyRoomResponse
import com.nbe2.infra.openapi.dto.EmergencyRoomResponse
import com.nbe2.infra.openapi.dto.OpenApiResponse
import com.nbe2.infra.openapi.dto.TraumaCenterResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(
    name = "openApiFeignClient",
    url = "https://apis.data.go.kr/B552657/ErmctInfoInqireService",
    configuration = [OpenApiFeignClientConfiguration::class]
)
interface OpenApiFeignClient {
    @GetMapping(value = ["/getEmrrmRltmUsefulSckbdInfoInqire"])
    fun getRealTimeEmergencyData(
        @RequestParam STAGE1: String?,
        @RequestParam(required = false) STAGE2: String?,
        @RequestParam(required = false) numOfRows: Int
    ): OpenApiResponse<List<RealTimeEmergencyDataResponse>>

    @GetMapping(value = ["/getEgytListInfoInqire"])
    fun getAllEmergencyData(
        @RequestParam(value = "pageStartNum", required = false) pageStartNum: Int,
        @RequestParam(value = "numOfRows", required = false) numOfRows: Int
    ): OpenApiResponse<List<AllEmergencyRoomResponse>>

    @GetMapping(value = ["/getEgytBassInfoInqire"])
    fun getEmergencyInfoData(
        @RequestParam(value = "HPID", required = false) hpid: String?,
        @RequestParam(value = "pageNo", required = false) pageStartNum: Int,
        @RequestParam(value = "numOfRows", required = false) numOfRows: Int
    ): OpenApiResponse<EmergencyRoomResponse>

    @GetMapping(value = ["/getStrmListInfoInqire"])
    fun getAllTraumaCenterData(
        @RequestParam(value = "pageNo", required = false) pageStartNum: Int,
        @RequestParam(value = "numOfRows", required = false) numOfRows: Int
    ): OpenApiResponse<List<AllEmergencyRoomResponse>>

    @GetMapping(value = ["/getEgytBassInfoInqire"])
    fun getTraumaCenterDataInfo(
        @RequestParam(value = "HPID", required = false) hpid: String?,
        @RequestParam(value = "pageNo", required = false) pageStartNum: Int,
        @RequestParam(value = "numOfRows", required = false) numOfRows: Int
    ): OpenApiResponse<TraumaCenterResponse>
}
