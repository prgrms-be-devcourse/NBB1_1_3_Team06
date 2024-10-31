package com.nbe2.domain.file

import jakarta.persistence.*
import lombok.*
import java.nio.charset.StandardCharsets

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "files")
class FileMetaData(
    val fileName: String,
    val path: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0;

    val encodedFileName: String
        get() = String(
            fileName.toByteArray(StandardCharsets.UTF_8),
            StandardCharsets.ISO_8859_1
        )

    companion object {
        fun of(originalFilename: String?, path: String): FileMetaData {
            return FileMetaData(
                originalFilename ?: "unknownFile",
                path
            )
        }
    }
}
