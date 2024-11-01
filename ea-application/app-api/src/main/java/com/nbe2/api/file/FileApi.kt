package com.nbe2.api.file

import com.nbe2.api.global.dto.Response
import com.nbe2.api.global.dto.Response.Companion.success
import com.nbe2.api.global.util.FileUtils.getContentDescription
import com.nbe2.api.global.util.FileUtils.probeContentType
import com.nbe2.api.global.util.FileUtils.saveFile
import com.nbe2.api.global.util.FileUtils.validate
import com.nbe2.domain.file.FileMetaDataService
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/files")
class FileApi(private val fileMetaDataService: FileMetaDataService) {

    @PostMapping
    fun uploadFile(@RequestPart file: MultipartFile?): Response<Long> {
        val fileMetaData = saveFile(file)
        return success(fileMetaDataService.save(fileMetaData))
    }

    @GetMapping("/{fileId}/download")
    fun download(@PathVariable fileId: Long): ResponseEntity<Resource> {
        val fileMetaData = fileMetaDataService.getFileMetaData(fileId)
        val file = validate(fileMetaData.path)
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        getContentDescription(fileMetaData.encodedFileName),
                )
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_OCTET_STREAM_VALUE,
                )
                .header(HttpHeaders.CONTENT_LENGTH, file.length().toString())
                .body(FileSystemResource(file))
    }

    @GetMapping("/{fileId}")
    fun getFile(@PathVariable fileId: Long): ResponseEntity<Resource> {
        val fileMetaData = fileMetaDataService.getFileMetaData(fileId)
        val file = validate(fileMetaData.path)
        val resource = UrlResource.from(file.toPath().normalize().toUri())
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        probeContentType(file),
                ) // MIME 타입 설정
                .body(resource)
    }
}
