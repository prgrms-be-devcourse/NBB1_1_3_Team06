package com.nbe2.infra.emergencyroom.client


import com.nbe2.domain.emergencyroom.*
import com.nbe2.infra.naver.client.NaverApiClient
import com.nbe2.infra.naver.dto.NaverDirectionsResponse
import com.nbe2.infra.openapi.client.OpenApiFeignClient
import com.nbe2.infra.openapi.dto.AllEmergencyRoomResponse
import org.springframework.stereotype.Component

@Component
class EmergencyRoomApiClient(
    private val openApiFeignClient: OpenApiFeignClient,
    private val naverApiClient: NaverApiClient
) : EmergencyRoomClient {


//    override fun getRealTimeInfo(region: Region): List<RealTimeEmergencyRoomInfo> {
//        return openApiFeignClient
//            .getRealTimeEmergencyData(region.region(), region.subRegion(), NUM_OF_ROWS)
//            .items
//            .map { it.toRealTimeEmergencyInfo() }
//    }

    override fun getRealTimeInfo(region: Region): MutableList<RealTimeEmergencyRoomInfo> {
        TODO("Not yet implemented")
    }

    override fun getEmergencyRoomInfoData(): List<EmergencyRoomInfo> {
        return getEmergencyData() + getTraumaCenterData()
    }

    override fun directionsEmergencyRoom(start: String, end: String): EmergencyRoomDirectionsInfo {
        val realTimeEmergencyData = naverApiClient.getEmergencyDirectionsData(start, end, "trafast")
        return NaverDirectionsResponse.form(realTimeEmergencyData)
    }

    private fun getEmergencyData(): List<EmergencyRoomInfo> {
        return getAllEmergencyRoomData().parallelStream()
            .map { ed -> openApiFeignClient.getEmergencyInfoData(ed.hpid, 1, 1000).items }
            .map { it.toEmergencyRoomInfo() }
            .toList()
    }

    private fun getTraumaCenterData(): List<EmergencyRoomInfo> {
        return getAllTraumaCenterData().parallelStream()
            .map { tc -> openApiFeignClient.getTraumaCenterDataInfo(tc.hpid, 1, 20).items }
            .map { it.toEmergencyRoomInfo() }
            .toList()
    }


    private fun getAllTraumaCenterData(): List<AllEmergencyRoomResponse> {
        return openApiFeignClient.getAllTraumaCenterData(1, 20).items
    }

    private fun getAllEmergencyRoomData(): List<AllEmergencyRoomResponse> {
        return openApiFeignClient.getAllEmergencyData(1, 1000).items
    }

    companion object {
        private const val NUM_OF_ROWS = 1000
    }
}