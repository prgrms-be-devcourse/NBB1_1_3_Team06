package com.nbe2.domain.file.exception

import com.nbe2.common.exception.DomainException

object FileMetaDataNotFoundException : DomainException(FileMetaDataErrorCode.FILE_META_DATA_NOT_FOUND_ERROR)
