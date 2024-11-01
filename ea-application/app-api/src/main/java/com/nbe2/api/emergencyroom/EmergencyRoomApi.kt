package com.nbe2.api.emergencyroom


import com.nbe2.api.emergencyroom.dto.EmergencyRoomDirectionsResponse
import com.nbe2.api.emergencyroom.dto.EmergencyRoomMapResponse
import com.nbe2.api.emergencyroom.dto.RealTimeEmergencyRoomResponse
import com.nbe2.api.global.dto.Response
import com.nbe2.domain.emergencyroom.Coordinate
import com.nbe2.domain.emergencyroom.EmergencyRoomDetailInfo
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo
import com.nbe2.domain.emergencyroom.EmergencyRoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/emergency-rooms")
class EmergencyRoomApi(
    private val emergencyRoomService: EmergencyRoomService
) {

    @GetMapping("/init")
    fun init(): Response<Void> {
        emergencyRoomService.init()
        return Response.success("전국 응급실 데이터 저장 완료")
    }

    @GetMapping("/real-time")
    fun getRealTimeEmergencyRooms(longitude: Double, latitude: Double): Response<List<RealTimeEmergencyRoomResponse>> {
        val responses: List<RealTimeEmergencyRoomResponse> = emergencyRoomService
            .getRealTimeEmergencyRooms(Coordinate.of(longitude, latitude))
            .stream()
            .map(RealTimeEmergencyRoomResponse::from)
            .toList()
        return Response.success(responses)
    }

    @GetMapping("/search")
    fun saveSearEmergency(hospitalName: String): Response<List<String>> {
        val emergencyRoomListForName: List<String> = emergencyRoomService.getEmergencyRoomListForName(hospitalName)
        return Response.success(emergencyRoomListForName)
    }

    @GetMapping("/directions")
    fun directionsEmergency(myLocation: String, hospitalName: String
    ): Response<EmergencyRoomDirectionsResponse> {
        val emergencyRoomDirectionsInfo: EmergencyRoomDirectionsInfo =
            emergencyRoomService.directionsEmergencyRoom(myLocation, hospitalName)
        val emergencyRoomDirectionsResponse: EmergencyRoomDirectionsResponse =
            EmergencyRoomDirectionsResponse.to(emergencyRoomDirectionsInfo)
        return Response.success(emergencyRoomDirectionsResponse)
    }

    @GetMapping("/map")
    fun getEmergencyRooms(
        longitude: Double, latitude: Double, distance: Double
    ): Response<List<EmergencyRoomMapResponse>> {
        val responses: List<EmergencyRoomMapResponse> = emergencyRoomService
            .getEmergencyRooms(Coordinate.of(longitude, latitude), distance)
            .stream()
            .map(EmergencyRoomMapResponse::from)
            .toList()
        return Response.success(responses)
    }

    @GetMapping
    fun getEmergencyRoomDetail(
        hospitalId: String, longitude: Double, latitude: Double
    ): Response<EmergencyRoomDetailInfo> {
        val emergencyRoomDetail: EmergencyRoomDetailInfo = emergencyRoomService.getEmergencyRoomDetail(
            hospitalId, Coordinate.of(longitude, latitude)
        )
        return Response.success(emergencyRoomDetail)
    }
}
