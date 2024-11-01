package com.nbe2.domain.emergencyroom.exception

import com.nbe2.common.constants.EAConstants.BAD_REQUEST
import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason
import com.nbe2.common.exception.ErrorReason.Companion.of
import com.nbe2.domain.file.exception.FileMetaDataNotFoundException.errorCode

enum class EmergencyRoomErrorCode(
        private val status: Int,
        private val errorCode: String,
        private val message: String,
) : BaseErrorCode {
    REGION_NOT_FOUND_ERROR(BAD_REQUEST, "EMR_400_1", "좌표에 해당하는 지역을 찾을 수 없습니다"),
    EMERGENCY_ROOM_NOT_FOUND_ERROR(
            BAD_REQUEST,
            "EMR_400_2",
            "해당 ID의 응급실 정보가 없습니다",
    ),
    INVALID_COORDINATE(BAD_REQUEST, "EMR_400_4", "유효하지 않은 좌표입니다"),
    REAL_TIME_EMERGENCY_ROOM_INFO_NOT_FOUND_ERROR(
            BAD_REQUEST,
            "EMR_400_3",
            "해당 ID의 실시간 응급실 정보가 없습니다",
    );

    override val errorReason: ErrorReason
        get() = of(status, errorCode, message)
}
