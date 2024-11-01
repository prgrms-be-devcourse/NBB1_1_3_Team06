package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AllEmergencyRoomResponse(
        // 주소
        @JsonProperty("dutyAddr") val dutyAddr: String,
        // 응급실 코드
        @JsonProperty("dutyEmcls") val dutyEmcls: String,
        // 응급실 이름
        @JsonProperty("dutyEmclsName") val dutyEmclsName: String,
        // 병원 이름
        @JsonProperty("dutyName") val dutyName: String,
        // 대표 전화
        @JsonProperty("dutyTel1") val dutyTel1: String,
        // 비상 전화
        @JsonProperty("dutyTel3") val dutyTel3: String,
        // 병원 ID
        @JsonProperty("hpid") val hpid: String,
        // 병원 ID (다른 명칭)
        @JsonProperty("phpid") val phpid: String,
        // 레코드 번호
        @JsonProperty("rnum") val rnum: Int,
        // 위도
        @JsonProperty("wgs84Lat") val wgs84Lat: Double,
        // 경도
        @JsonProperty("wgs84Lon") val wgs84Lon: Double,
)
