package com.nbe2.domain.file.exception

import com.nbe2.common.exception.BaseErrorCode
import com.nbe2.common.exception.ErrorReason
import com.nbe2.common.exception.ErrorReason.Companion.of

enum class FileMetaDataErrorCode(
    private val status: Int,
    private val errorCode: String,
    private val message: String
) : BaseErrorCode {
    FILE_META_DATA_NOT_FOUND_ERROR(400, "FMD_400_1", "해당 ID의 파일 메타데이터가 없습니다");

    override val errorReason: ErrorReason
        get() = of(status, errorCode, message)
}
