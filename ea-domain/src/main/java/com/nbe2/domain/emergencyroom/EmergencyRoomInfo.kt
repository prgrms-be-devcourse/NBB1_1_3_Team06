package com.nbe2.domain.emergencyroom


data class EmergencyRoomInfo(
    val id: String,
    val hospitalName: String,
    val zipCode: String,
    val address: String,
    val mainContactNumber: String,
    val emergencyRoomContactNumber: String,
    val simpleMap: String,
    val emergencyRoomAvailability: Boolean,
    val longitude: String,
    val latitude: String,
    val medicalDepartments: String,
    val totalBedCount: Int,
    val thoracicIcuBedCount: Int,
    val neurologicalIcuBedCount: Int,
    val emergencyRoomBedCount: Int,
    val generalWardBedCount: Int,
    val generalIcuBedCount: Int,
    val neonatalIcuBedCount: Int,
    val operatingRoomBedCount: Int
) {

    fun toEmergencyRoom(): EmergencyRoom {
        // 좌표 변환
        val coordinate = if (longitude.toDouble() < 90.0) {
            Coordinate.of(latitude.toDouble(), longitude.toDouble())
        } else {
            Coordinate.of(longitude.toDouble(), latitude.toDouble())
        }

        // 침대 수 정보 생성
        val bedCount = EmergencyRoom.BedCount(
            totalBedCount = totalBedCount,
            thoracicIcuBedCount = thoracicIcuBedCount,
            neurologicalIcuBedCount = neurologicalIcuBedCount,
            emergencyRoomBedCount = emergencyRoomBedCount,
            generalWardBedCount = generalWardBedCount,
            generalIcuBedCount = generalIcuBedCount,
            neonatalIcuBedCount = neonatalIcuBedCount,
            operatingRoomBedCount = operatingRoomBedCount
        )

        return EmergencyRoom(
            hpId = id,
            hospitalName = hospitalName,
            zipCode = zipCode,
            address = address,
            mainContactNumber = mainContactNumber,
            emergencyRoomContactNumber = emergencyRoomContactNumber,
            simpleMap = simpleMap,
            emergencyRoomAvailability = emergencyRoomAvailability,
            bedCount = bedCount,
            location = EmergencyRoom.coordinateToPoint(coordinate),
            medicalDepartments = medicalDepartments
        )
    }
}
