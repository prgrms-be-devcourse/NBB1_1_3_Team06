package com.nbe3.domain.posts

import com.nbe3.domain.posts.PostFilePk
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "post_files")
class PostFile private constructor(
    fileMetaData: FileMetaData,
    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    var post: Post
) {

    @EmbeddedId
    var postFilePk = PostFilePk()

    @MapsId("fileMetaDataId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    var fileMetaData: FileMetaData = fileMetaData

    init {
        this.postFilePk = PostFilePk.of(fileMetaData.getId(), post.id ?: throw IllegalArgumentException())
        this.fileMetaData = fileMetaData
        setPost(post)
    }

    val fileMetaDataId: Long
        get() = fileMetaData.getId()

    val postId: Long?
        get() = post.id

    // ** 연관관계 편의 메서드**//
    fun setPost(post: Post) {
        this.post = post
        post.addFile(this)
    }

    companion object {
        fun of(fileMetaData: FileMetaData, post: Post): PostFile {
            return PostFile(fileMetaData, post)
        }
    }
}
