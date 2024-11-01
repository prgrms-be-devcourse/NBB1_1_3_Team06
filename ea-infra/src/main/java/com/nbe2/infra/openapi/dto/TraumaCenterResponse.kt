package com.nbe2.infra.openapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nbe2.domain.emergencyroom.EmergencyRoomInfo

data class TraumaCenterResponse(
        @JsonProperty("hpid") val hpid: String, // 기관 ID
        @JsonProperty("dutyName") val dutyName: String, // 기관명
        @JsonProperty("postCdn1") val postCdn1: String, // 우편번호1
        @JsonProperty("postCdn2") val postCdn2: String, // 우편번호2
        @JsonProperty("dutyAddr") val dutyAddr: String, // 주소
        @JsonProperty("dutyTel1") val dutyTel1: String, // 대표전화1
        @JsonProperty("dutyTel3") val dutyTel3: String, // 응급실전화
        @JsonProperty("hvec") val hvec: String, // 응급실
        @JsonProperty("hvoc") val hvoc: String, // 신경중환자
        @JsonProperty("hvcc") val hvcc: String, // 신생중환자
        @JsonProperty("hvncc") val hvncc: String, // 흉부중환자
        @JsonProperty("hvccc") val hvccc: String, // 일반중환자
        @JsonProperty("hvicc") val hvicc: String, // 입원실
        @JsonProperty("hvgc") val hvgc: String, // 입원실 가용 여부(1/2)
        @JsonProperty("dutyHayn") val dutyHayn: String, // 병상수
        @JsonProperty("dutyHano") val dutyHano: String, // 기관설명 상세
        @JsonProperty("dutyInf") val dutyInf: String, // 간이 약도
        @JsonProperty("dutyMapimg") val dutyMapimg: String, // 응급실 운영 여부(1/2)
        @JsonProperty("dutyEryn") val dutyEryn: String, // 응급실 운영 여부
        @JsonProperty("dutyTime1c") val dutyTime1c: String, // 진료시간(월요일)C
        @JsonProperty("dutyTime2c") val dutyTime2c: String, // 진료시간(화요일)C
        @JsonProperty("dutyTime3c") val dutyTime3c: String, // 진료시간(수요일)C
        @JsonProperty("dutyTime4c") val dutyTime4c: String, // 진료시간(목요일)C
        @JsonProperty("dutyTime5c") val dutyTime5c: String, // 진료시간(금요일)C
        @JsonProperty("dutyTime6c") val dutyTime6c: String, // 진료시간(토요일)C
        @JsonProperty("dutyTime7c") val dutyTime7c: String, // 진료시간(일요일)C
        @JsonProperty("dutyTime8c") val dutyTime8c: String, // 진료시간(공휴일)C
        @JsonProperty("dutyTime1s") val dutyTime1s: String, // 진료시간(월요일)S
        @JsonProperty("dutyTime2s") val dutyTime2s: String, // 진료시간(화요일)S
        @JsonProperty("dutyTime3s") val dutyTime3s: String, // 진료시간(수요일)S
        @JsonProperty("dutyTime4s") val dutyTime4s: String, // 진료시간(목요일)S
        @JsonProperty("dutyTime5s") val dutyTime5s: String, // 진료시간(금요일)S
        @JsonProperty("dutyTime6s") val dutyTime6s: String, // 진료시간(토요일)S
        @JsonProperty("dutyTime7s") val dutyTime7s: String, // 진료시간(일요일)S
        @JsonProperty("dutyTime8s") val dutyTime8s: String, // 진료시간(공휴일)S
        @JsonProperty("MKioskTy25") val MKioskTy25: String, // 응급의학 서비스
        @JsonProperty("MKioskTy1") val MKioskTy1: String, // 뇌출혈 수술
        @JsonProperty("MKioskTy2") val MKioskTy2: String, // 뇌경색 재관류
        @JsonProperty("MKioskTy3") val MKioskTy3: String, // 심근경색 재관류
        @JsonProperty("MKioskTy4") val MKioskTy4: String, // 복부 손상 수술
        @JsonProperty("MKioskTy5") val MKioskTy5: String, // 사지 접합 수술
        @JsonProperty("MKioskTy6") val MKioskTy6: String, // 응급 내시경
        @JsonProperty("MKioskTy7") val MKioskTy7: String, // 응급 투석
        @JsonProperty("MKioskTy8") val MKioskTy8: String, // 정신질환자 응급 관리
        @JsonProperty("MKioskTy9") val MKioskTy9: String, // 신생아 응급 관리
        @JsonProperty("MKioskTy10") val MKioskTy10: String, // 중증 화상 치료
        @JsonProperty("MKioskTy11") val MKioskTy11: String, // 뇌출혈 수술
        @JsonProperty("wgs84Lon") val wgs84Lon: Double, // 병원 경도
        @JsonProperty("wgs84Lat") val wgs84Lat: Double, // 병원 위도
        @JsonProperty("dgidIdName") val dgidIdName: String, // 진료 과목
        @JsonProperty("hpbdn") val hpbdn: Int, // 병상수
        @JsonProperty("hpccuyn") val hpccuyn: Int, // 흉부중환자실
        @JsonProperty("hpcuyn") val hpcuyn: Int, // 신경중환자실
        @JsonProperty("hperyn") val hperyn: Int, // 입원실 가용 여부
        @JsonProperty("hpgryn") val hpgryn: Int, // 일반중환자실
        @JsonProperty("hpicuyn") val hpicuyn: Int, // 신생아중환자실
        @JsonProperty("hpnicuyn") val hpnicuyn: Int, // 수술실 가용 여부
        @JsonProperty("hpopyn") val hpopyn: Int, // 수술실
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
                operatingRoomBedCount = hpopyn,
        )
    }

    private fun convertorBoolean(value: String): Boolean {
        return value.equals("Y", ignoreCase = true)
    }
}
