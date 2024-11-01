package com.nbe2.api.emergencyroom.dto

import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomWithDistance

data class RealTimeEmergencyRoomResponse(
    val hpId: String,  // 병원 ID
    val hospitalName: String,  // 병원 이름
    val emergencyPhone: String,  // 응급실 전화번호
    val inputDate: String,  // 입력 일시
    val availableBeds: Int,  // 응급실 가용 병상 수
    val operatingRoomBeds: Int,  // 수술실 가용 병상 수
    val neuroIcuBeds: Int,  // 신경과 중환자실 가용 병상 수
    val neonatalIcuBeds: Int,  // 신생아 중환자실 가용 병상 수
    val chestIcuBeds: Int,  // 흉부외과 중환자실 가용 병상 수
    val generalIcuBeds: Int,  // 일반 중환자실 가용 병상 수
    val generalWardBeds: Int,  // 입원실 가용 병상 수
    val isCtAvailable: Boolean,  // CT 가용 여부 (Y: 가능, N: 불가)
    val isMriAvailable: Boolean,  // MRI 가용 여부 (Y: 가능, N: 불가)
    val isAngiographyAvailable: Boolean,  // 혈관촬영기 가용 여부 (Y: 가능, N: 불가)
    val isVentilatorAvailable: Boolean,  // 인공호흡기 가용 여부 (Y: 가능, N: 불가)
    val isIncubatorAvailable: Boolean,  // 인큐베이터 가용 여부 (Y: 가능, N: 불가)
    val isAmbulanceAvailable: Boolean,  // 구급차 가용 여부 (Y: 가능, N: 불가))
    val distance: Double
) {
    companion object {
        fun from(
            realTimeEmergencyRoomWithDistance: RealTimeEmergencyRoomWithDistance
        ): RealTimeEmergencyRoomResponse {
            return RealTimeEmergencyRoomResponse(
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.hpId,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.hospitalName,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.emergencyPhone,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.inputDate,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.availableBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.operatingRoomBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.neuroIcuBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.neonatalIcuBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.chestIcuBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.generalIcuBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.generalWardBeds,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.isCtAvailable,
                realTimeEmergencyRoomWithDistance.realTimeEmergencyRoomInfo.isMriAvailable,
                realTimeEmergencyRoomWithDistance
                    .realTimeEmergencyRoomInfo
                    .isAngiographyAvailable,
                realTimeEmergencyRoomWithDistance
                    .realTimeEmergencyRoomInfo
                    .isVentilatorAvailable,
                realTimeEmergencyRoomWithDistance
                    .realTimeEmergencyRoomInfo
                    .isIncubatorAvailable,
                realTimeEmergencyRoomWithDistance
                    .realTimeEmergencyRoomInfo
                    .isAmbulanceAvailable,
                realTimeEmergencyRoomWithDistance.distance
            )
        }
    }
}
