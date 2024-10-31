package com.nbe2.domain.emergencyroom

import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class EmergencyRoomInitializer(
    private val emergencyRoomRepository: EmergencyRoomRepository,
    private val emergencyRoomClient: EmergencyRoomClient
) {
    //    public void init() {
    //        emergencyRoomRepository.saveAll(
    //                emergencyRoomClient.getEmergencyRoomInfoData().stream()
    //                        .map(EmergencyRoomInfo::toEmergencyRoom)
    //                        .toList());
    //    }
    fun init() {
        val uniqueKeySet = uniqueKeySet
        val emergencyRooms: List<EmergencyRoom> = removeDuplicatesEmergencyData(uniqueKeySet)
        if (emergencyRooms.isNotEmpty()) emergencyRoomRepository.saveAll(emergencyRooms)
    }

    private val uniqueKeySet: Set<String>
        get() = emergencyRoomRepository.findAll().stream()
            .map { rooms ->
                (rooms.hospitalName
                        + rooms.getLocation().longitude
                        + rooms.getLocation().latitude)
            }
            .collect(Collectors.toSet())

    private fun removeDuplicatesEmergencyData(uniqueKeySet: Set<String>): List<EmergencyRoom> {
        // nullable인 경우 처리
        return emergencyRoomClient.getEmergencyRoomInfoData()
            .map { it.toEmergencyRoom() } // EmergencyRoomInfo를 EmergencyRoom으로 변환
            .filter { room ->
                // 병원 이름, 경도, 위도를 연결해 고유 키 생성
                val uniqueKey = "${room.hospitalName}${room.location.x}${room.location.y}"
                !uniqueKeySet.contains(uniqueKey) // 고유 키가 없는 데이터만 필터링
            }
            //?: emptyList() // nullable 처리: null이면 빈 리스트 반환
    }
}
