package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nbe2.domain.emergencyroom.EmergencyRoomInfo

data class EmergencyRoomResponse(
    // 진료과목
    @JsonProperty("dgidIdName") val dgidIdName: String,
    // 기관ID
    @JsonProperty("hpid") val hpid: String,
    // 기관명
    @JsonProperty("dutyName") val dutyName: String,
    // 우편번호1
    @JsonProperty("postCdn1") val postCdn1: String,
    // 우편번호2
    @JsonProperty("postCdn2") val postCdn2: String,
    // 주소
    @JsonProperty("dutyAddr") val dutyAddr: String,
    // 대표전화1
    @JsonProperty("dutyTel1") val dutyTel1: String,
    // 응급실전화
    @JsonProperty("dutyTel3") val dutyTel3: String,
    // 응급실 가용 여부
    @JsonProperty("hvec") val hvec: Int,
    // 수술실
    @JsonProperty("hvoc") val hvoc: Int,
    // 신경중환자
    @JsonProperty("hvcc") val hvcc: Int,
    // 신생중환자
    @JsonProperty("hvncc") val hvncc: Int,
    // 일반중환자
    @JsonProperty("hvccc") val hvccc: Int,
    // 입원실
    @JsonProperty("hvicc") val hvicc: Int,
    // 입원실 가용 여부 (1/2)
    @JsonProperty("hvgc") val hvgc: Int,
    // 병상 수
    @JsonProperty("dutyHayn") val dutyHayn: Int,
    // 기관설명상세
    @JsonProperty("dutyHano") val dutyHano: Int,
    // 기관설명
    @JsonProperty("dutyInf") val dutyInf: String,
    // 간이 약도
    @JsonProperty("dutyMapimg") val dutyMapimg: String,
    // 응급실 운영 여부 (1/2)
    @JsonProperty("dutyEryn") val dutyEryn: Int,
    // 진료시간(월요일)C
    @JsonProperty("dutyTime1c") val dutyTime1c: String,
    // 진료시간(화요일)C
    @JsonProperty("dutyTime2c") val dutyTime2c: String,
    // 진료시간(수요일)C
    @JsonProperty("dutyTime3c") val dutyTime3c: String,
    // 진료시간(목요일)C
    @JsonProperty("dutyTime4c") val dutyTime4c: String,
    // 진료시간(금요일)C
    @JsonProperty("dutyTime5c") val dutyTime5c: String,
    // 진료시간(토요일)C
    @JsonProperty("dutyTime6c") val dutyTime6c: String,
    // 진료시간(일요일)C
    @JsonProperty("dutyTime7c") val dutyTime7c: String,
    // 진료시간(공휴일)C
    @JsonProperty("dutyTime8c") val dutyTime8c: String,
    // 진료시간(월요일)S
    @JsonProperty("dutyTime1s") val dutyTime1s: String,
    // 진료시간(화요일)S
    @JsonProperty("dutyTime2s") val dutyTime2s: String,
    // 진료시간(수요일)S
    @JsonProperty("dutyTime3s") val dutyTime3s: String,
    // 진료시간(목요일)S
    @JsonProperty("dutyTime4s") val dutyTime4s: String,
    // 진료시간(금요일)S
    @JsonProperty("dutyTime5s") val dutyTime5s: String,
    // 진료시간(토요일)S
    @JsonProperty("dutyTime6s") val dutyTime6s: String,
    // 진료시간(일요일)S
    @JsonProperty("dutyTime7s") val dutyTime7s: String,
    // 진료시간(공휴일)S
    @JsonProperty("dutyTime8s") val dutyTime8s: String,
    // 병원 위도
    @JsonProperty("wgs84Lat") val wgs84Lat: Double,
    // 병원 경도
    @JsonProperty("wgs84Lon") val wgs84Lon: Double,
    // 병상수
    @JsonProperty("hpbdn") val hpbdn: Int,
    // 흉부중환자실
    @JsonProperty("hpccuyn") val hpccuyn: Int,
    // 신경중환자실
    @JsonProperty("hpcuyn") val hpcuyn: Int,
    // 응급실 가용 여부
    @JsonProperty("hperyn") val hperyn: Int,
    // 일반중환자실
    @JsonProperty("hpgryn") val hpgryn: Int,
    // 신생아중환자실
    @JsonProperty("hpicuyn") val hpicuyn: Int,
    // 수술실 가용 여부
    @JsonProperty("hpnicuyn") val hpnicuyn: Int,
    // 수술실
    @JsonProperty("hpopyn") val hpopyn: Int
) {
    fun toEmergencyRoomInfo(): EmergencyRoomInfo {
        return EmergencyRoomInfo(
            id = hpid,
            hospitalName = dutyName,
            zipCode = postCdn1,
            address = dutyAddr,
            mainContactNumber = dutyTel1,
            emergencyRoomContactNumber = dutyTel3,
            simpleMap = dutyMapimg,
            emergencyRoomAvailability = convertorBoolean(dutyEryn),
            longitude = wgs84Lon.toString(),
            latitude = wgs84Lat.toString(),
            medicalDepartments = dgidIdName,
            totalBedCount = hpbdn,
            thoracicIcuBedCount = hpccuyn,
            neurologicalIcuBedCount = hpccuyn,
            emergencyRoomBedCount = hperyn,
            generalWardBedCount = hpgryn,
            generalIcuBedCount = hpicuyn,
            neonatalIcuBedCount = hpnicuyn,
            operatingRoomBedCount = hpopyn
        )
    }

    private fun convertorBoolean(value: Int): Boolean {
        return value == 1
    }
}
