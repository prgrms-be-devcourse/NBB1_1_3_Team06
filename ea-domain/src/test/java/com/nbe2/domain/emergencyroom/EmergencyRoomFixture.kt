package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.EmergencyRoom
import com.nbe2.domain.global.TestConstants

object EmergencyRoomFixture {
    const val HP_ID: String = "HP001"
    const val HOSPITAL_NAME: String = "서울종합병원"
    const val ZIP_CODE: String = "12345"
    const val ADDRESS: String = "서울특별시 중구 남대문로 123"
    const val MAIN_CONTACT_NUMBER: String = "02-123-4567"
    const val EMERGENCY_ROOM_CONTACT_NUMBER: String = "02-987-6543"
    const val SIMPLE_MAP: String = "서울종합병원 위치"
    const val EMERGENCY_ROOM_AVAILABILITY: Boolean = true
    val COORDINATE: Coordinate = Coordinate.of(126.9784, 37.5665)
    const val MEDICAL_DEPARTMENTS: String = "응급의학과, 흉부외과, 신경외과"
    const val TOTAL_BED_COUNT: Int = 100
    const val THORACIC_ICU_BED_COUNT: Int = 10
    const val NEUROLOGICAL_ICU_BED_COUNT: Int = 15
    const val EMERGENCY_ROOM_BED_COUNT: Int = 20
    const val GENERAL_WARD_BED_COUNT: Int = 30
    const val GENERAL_ICU_BED_COUNT: Int = 10
    const val NEONATAL_ICU_BED_COUNT: Int = 5
    const val OPERATING_ROOM_BED_COUNT: Int = 10
    const val DISTANCE: Double = 5.0
    const val EMERGENCY_PHONE: String = "02-987-6543"
    const val INPUT_DATE: String = "2024-10-10 12:00"

    @JvmStatic
    fun create(): EmergencyRoom {
        return EmergencyRoom.builder()
            .hpId(HP_ID)
            .hospitalName(HOSPITAL_NAME)
            .zipCode(ZIP_CODE)
            .address(ADDRESS)
            .mainContactNumber(MAIN_CONTACT_NUMBER)
            .emergencyRoomContactNumber(EMERGENCY_ROOM_CONTACT_NUMBER)
            .simpleMap(SIMPLE_MAP)
            .emergencyRoomAvailability(EMERGENCY_ROOM_AVAILABILITY)
            .coordinate(COORDINATE)
            .medicalDepartments(MEDICAL_DEPARTMENTS)
            .totalBedCount(TOTAL_BED_COUNT)
            .thoracicIcuBedCount(THORACIC_ICU_BED_COUNT)
            .neurologicalIcuBedCount(NEUROLOGICAL_ICU_BED_COUNT)
            .emergencyRoomBedCount(EMERGENCY_ROOM_BED_COUNT)
            .generalWardBedCount(GENERAL_WARD_BED_COUNT)
            .generalIcuBedCount(GENERAL_ICU_BED_COUNT)
            .neonatalIcuBedCount(NEONATAL_ICU_BED_COUNT)
            .operatingRoomBedCount(OPERATING_ROOM_BED_COUNT)
            .build()
    }

    @JvmStatic
    fun createWithId(): EmergencyRoom {
        val emergencyRoom = create()

        try {
            val field = EmergencyRoom::class.java.getDeclaredField("id")
            field.isAccessible = true
            field[emergencyRoom] = TestConstants.ID
        } catch (ignored: Exception) {
        }

        return emergencyRoom
    }

    fun createMapInfo(): EmergencyRoomMapInfo {
        return EmergencyRoomMapInfo(
            1L, HP_ID, HOSPITAL_NAME, ADDRESS, SIMPLE_MAP, COORDINATE, DISTANCE
        )
    }

    fun createRealTimeInfo(): RealTimeEmergencyRoomInfo {
        return RealTimeEmergencyRoomInfo(
            HP_ID,
            HOSPITAL_NAME,
            EMERGENCY_PHONE,
            INPUT_DATE,
            10,  // availableBeds
            2,  // operatingRoomBeds
            1,  // neuroIcuBeds
            0,  // neonatalIcuBeds
            1,  // chestIcuBeds
            3,  // generalIcuBeds
            5,  // generalWardBeds
            true,  // isCtAvailable
            true,  // isMriAvailable
            false,  // isAngiographyAvailable
            true,  // isVentilatorAvailable
            false,  // isIncubatorAvailable
            true // isAmbulanceAvailable
        )
    }

    fun createRealTimeInfoList(): List<RealTimeEmergencyRoomInfo> {
        return java.util.List.of(createRealTimeInfo(), createRealTimeInfo())
    }

    val region: Region
        get() = Region("서울특별시", "중구")
}
