package com.nbe2.api.global.util

import com.nbe2.api.global.exception.FileNotFoundException
import com.nbe2.domain.file.FileMetaData
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object FileUtils {
    private val BASE_PATH = basePath
    private const val RELATIVE_UPLOAD_PATH = "files/"
    private val UPLOAD_PATH = BASE_PATH + RELATIVE_UPLOAD_PATH

    private val basePath: String
        get() {
            try {
                return (Paths.get(
                    FileUtils::class.java
                        .protectionDomain
                        .codeSource
                        .location
                        .toURI()
                )
                    .parent
                    .parent
                    .parent
                    .parent
                    .toString()
                        + "/")
            } catch (e: Exception) {
                throw RuntimeException("Cannot determine base path.", e)
            }
        }

    @JvmStatic
    fun saveFile(uploadFile: MultipartFile?): FileMetaData? {
        if (uploadFile == null) {
            return null
        }

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
        return FileMetaData.of(uploadFile.originalFilename, UPLOAD_PATH + savedName)
    }

    @JvmStatic
    fun validate(path: String): File {
        val file = File(path)

        if (!file.exists()) {
            throw FileNotFoundException.EXCEPTION
        }
        return file
    }

    @JvmStatic
    fun probeContentType(file: File): String {
        try {
            return Files.probeContentType(file.toPath())
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
