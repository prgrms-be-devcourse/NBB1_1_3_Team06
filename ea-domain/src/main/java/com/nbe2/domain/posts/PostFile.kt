package com.nbe2.domain.posts

import com.nbe2.domain.file.FileMetaData
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "post_files")
class PostFile private constructor(
    @MapsId("fileMetaDataId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    var fileMetaData: FileMetaData,

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    var post: Post
) {

    @EmbeddedId
    var postFilePk = PostFilePk()

    init {
        this.postFilePk = PostFilePk.of(fileMetaData.id, post.id ?: throw IllegalArgumentException())
        associateWithPost(post)
    }

    val fileMetaDataId: Long
        get() = fileMetaData.id

    val postId: Long?
        get() = post.id

    // ** 연관관계 편의 메서드**//
    private fun associateWithPost(post: Post) {
        this.post = post
        post.addFile(this)
    }

    companion object {
        fun of(fileMetaData: FileMetaData, post: Post): PostFile {
            return PostFile(fileMetaData, post)
        }
    }
}
