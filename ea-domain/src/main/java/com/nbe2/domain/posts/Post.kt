package com.nbe2.domain.posts

import com.nbe2.domain.global.BaseTimeEntity
import com.nbe2.domain.user.User
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "posts")
class Post
private constructor(
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User,
        var title: String,
        var content: String,
        @Enumerated(EnumType.STRING) var city: City,
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    var id: Long? = null

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    val comments: MutableList<Comment> = LinkedList<Comment>()

    var commentCount: Long = 0

    var likeCount: Long = 0

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    val postFiles: MutableList<PostFile> = LinkedList<PostFile>()

    val writerName: String
        get() = user.name

    // ** 연관관계 편의 메서드 **//
    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    // ** business logic **//
    fun update(title: String, content: String, city: City): Post {
        this.title = title
        this.content = content
        this.city = city
        return this
    }

    fun addFile(file: PostFile) {
        postFiles.add(file)
    }

    fun increaseCommentCount() {
        commentCount++
    }

    fun decreaseCommentCount() {
        if (commentCount <= 0) return
        commentCount--
    }

    fun increaseLikeCount() {
        likeCount++
    }

    fun decreaseLikeCount() {
        if (likeCount <= 0) return
        likeCount--
    }

    companion object {
        fun create(
                user: User,
                title: String,
                content: String,
                city: City,
        ): Post {
            return Post(user, title, content, city)
        }
    }
}
