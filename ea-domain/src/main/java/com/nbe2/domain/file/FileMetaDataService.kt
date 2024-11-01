package com.nbe2.domain.file

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class FileMetaDataService(
        private val fileMetaDataAppender: FileMetaDataAppender,
        private val fileMetaDataReader: FileMetaDataReader,
) {

    fun save(fileMetaData: FileMetaData): Long {
        return fileMetaDataAppender.append(fileMetaData)
    }

    fun getFileMetaData(fileId: Long): FileMetaData {
        return fileMetaDataReader.read(fileId)
    }
}
