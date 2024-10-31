package com.nbe2.domain.file

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class FileMetaDataAppender(
    private val fileMetaDateRepository: FileMetaDateRepository
) {
    fun append(fileMetaData: FileMetaData): Long {
        val saved = fileMetaDateRepository.save(fileMetaData)
        return saved.id
    }
}
