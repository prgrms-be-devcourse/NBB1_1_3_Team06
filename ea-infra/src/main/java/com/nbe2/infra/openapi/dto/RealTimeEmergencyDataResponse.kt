package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo

data class RealTimeEmergencyDataResponse(
    @field:JsonProperty("hpid") @param:JsonProperty(
        "hpid"
    ) val hospitalId: String,  // 병원 ID
    @field:JsonProperty("dutyName") @param:JsonProperty(
        "dutyName"
    ) val hospitalName: String,  // 병원 이름
    @field:JsonProperty("dutyTel3") @param:JsonProperty(
        "dutyTel3"
    ) val emergencyPhone: String,  // 응급실 전화번호
    @field:JsonProperty("hvidate") @param:JsonProperty(
        "hvidate"
    ) val inputDate: String,  // 입력 일시
    @field:JsonProperty("hvec") @param:JsonProperty(
        "hvec"
    ) val availableBeds: Int,  // 응급실 가용 병상 수
    @field:JsonProperty("hvoc") @param:JsonProperty(
        "hvoc"
    ) val operatingRoomBeds: Int,  // 수술실 가용 병상 수
    @field:JsonProperty("hvcc") @param:JsonProperty(
        "hvcc"
    ) val neuroIcuBeds: Int,  // 신경과 중환자실 가용 병상 수
    @field:JsonProperty("hvncc") @param:JsonProperty(
        "hvncc"
    ) val neonatalIcuBeds: Int,  // 신생아 중환자실 가용 병상 수
    @field:JsonProperty("hvccc") @param:JsonProperty(
        "hvccc"
    ) val chestIcuBeds: Int,  // 흉부외과 중환자실 가용 병상 수
    @field:JsonProperty("hvicc") @param:JsonProperty(
        "hvicc"
    ) val generalIcuBeds: Int,  // 일반 중환자실 가용 병상 수
    @field:JsonProperty("hvgc") @param:JsonProperty(
        "hvgc"
    ) val generalWardBeds: Int,  // 입원실 가용 병상 수
    @field:JsonProperty("hvctayn") @param:JsonProperty(
        "hvctayn"
    ) val ctAvailable: String,  // CT 가용 여부 (Y: 가능, N: 불가)
    @field:JsonProperty("hvmriayn") @param:JsonProperty(
        "hvmriayn"
    ) val mriAvailable: String,  // MRI 가용 여부 (Y: 가능, N: 불가)
    @field:JsonProperty("hvangioayn") @param:JsonProperty(
        "hvangioayn"
    ) val angiographyAvailable: String,  // 혈관촬영기 가용 여부 (Y: 가능, N: 불가)
    @field:JsonProperty("hvventiayn") @param:JsonProperty(
        "hvventiayn"
    ) val ventilatorAvailable: String,  // 인공호흡기 가용 여부 (Y: 가능, N: 불가)
    @field:JsonProperty("hvincuayn") @param:JsonProperty(
        "hvincuayn"
    ) val incubatorAvailable: String,  // 인큐베이터 가용 여부 (Y: 가능, N: 불가)
    @field:JsonProperty("hvamyn") @param:JsonProperty(
        "hvamyn"
    ) val ambulanceAvailable: String // 구급차 가용 여부 (Y: 가능, N: 불가))
) {
    fun toRealTimeEmergencyInfo(): RealTimeEmergencyRoomInfo {
        return RealTimeEmergencyRoomInfo(
            hospitalId,
            hospitalName,
            emergencyPhone,
            inputDate,
            availableBeds,
            operatingRoomBeds,
            neuroIcuBeds,
            neonatalIcuBeds,
            chestIcuBeds,
            generalIcuBeds,
            generalWardBeds,
            convertToBoolean(ctAvailable),
            convertToBoolean(mriAvailable),
            convertToBoolean(angiographyAvailable),
            convertToBoolean(ventilatorAvailable),
            convertToBoolean(incubatorAvailable),
            convertToBoolean(ambulanceAvailable)
        )
    }

    private fun convertToBoolean(value: String): Boolean {
        return "Y".equals(value, ignoreCase = true)
    }
}
