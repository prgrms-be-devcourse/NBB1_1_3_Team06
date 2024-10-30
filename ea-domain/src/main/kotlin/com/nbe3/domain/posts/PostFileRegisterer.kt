package com.nbe3.domain.posts

import com.nbe3.domain.file.FileMetaData
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class PostFileRegisterer (
    private val postFileRepository: PostFileRepository,
    private val fileMetaDataReader: FileMetaDataReader){

    fun register(post: Post, fileIdList: Optional<List<Long?>>) {
        postFileRepository.deleteByPostId(post.id)
        fileIdList.ifPresent { fileIds: List<Long?>? ->
            val fileMetaDatas: List<FileMetaData> = fileMetaDataReader.readAll(fileIds)
            val postFiles =
                fileMetaDatas.stream().map<PostFile?>(Function<FileMetaData, PostFile?> { data: FileMetaData? ->
                    PostFile.of(
                        data,
                        post
                    )
                }).toList()
            postFileRepository.saveAll(postFiles)
        }
    }
}
