package com.nbe2.domain.file

import com.nbe2.domain.file.exception.FileMetaDataNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class FileMetaDataReader(
    private val fileMetaDateRepository: FileMetaDateRepository
) {
    fun read(fileId: Long): FileMetaData {
        return fileMetaDateRepository
            .findById(fileId)
            .orElseThrow {FileMetaDataNotFoundException }
    }

    fun readAll(fileIds: List<Long?>): List<FileMetaData> {
        return fileMetaDateRepository.findAllById(fileIds)
    }
}
