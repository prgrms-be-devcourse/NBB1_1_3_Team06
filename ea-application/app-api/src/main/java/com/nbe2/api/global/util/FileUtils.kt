package com.nbe2.api.global.util

import com.nbe2.api.global.exception.FileNotFoundException
import com.nbe2.domain.file.FileMetaData
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import org.springframework.web.multipart.MultipartFile

object FileUtils {
    private val BASE_PATH = basePath
    private const val RELATIVE_UPLOAD_PATH = "files/"
    private val UPLOAD_PATH = BASE_PATH + RELATIVE_UPLOAD_PATH

    private val basePath: String
        get() {
            try {
                return (Paths.get(
                                FileUtils::class
                                        .java
                                        .protectionDomain
                                        .codeSource
                                        .location
                                        .toURI()
                        )
                        .parent
                        .parent
                        .parent
                        .parent
                        .toString() + "/")
            } catch (e: Exception) {
                throw RuntimeException("Cannot determine base path.", e)
            }
        }

    fun saveFile(uploadFile: MultipartFile?): FileMetaData {
        requireNotNull(uploadFile) { "File is null" }

        if (!File(UPLOAD_PATH).exists()) {
            File(UPLOAD_PATH).mkdir()
        }

        val savedName = UUID.randomUUID().toString()
        val savedTradingFile = File(UPLOAD_PATH + savedName)

        try {
            uploadFile.transferTo(savedTradingFile)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return FileMetaData.of(
                uploadFile.originalFilename,
                UPLOAD_PATH + savedName,
        )
    }

    fun validate(path: String): File {
        val file = File(path)

        if (!file.exists()) {
            throw FileNotFoundException
        }
        return file
    }

    fun probeContentType(file: File): String {
        try {
            return Files.probeContentType(file.toPath())
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun getContentDescription(encodedFileName: String): String {
        return "attachment;filename=\"$encodedFileName\""
    }
}
