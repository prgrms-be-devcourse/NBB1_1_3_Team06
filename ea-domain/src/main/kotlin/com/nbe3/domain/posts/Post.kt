package com.nbe3.domain.posts

import com.nbe3.domain.global.BaseTimeEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "posts")
open class Post private constructor(user: User, title: String, content: String, city: City) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    open val user: User = user

    open var title: String = title

    open var content: String = content

    @Enumerated(EnumType.STRING)
    open var city: City = city

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    open val comments: MutableList<Comment> = LinkedList<Comment>()

    open var commentCount: Long = 0

    open var likeCount: Long = 0

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    open val postFiles: MutableList<PostFile> = LinkedList<PostFile>()

    init {
        this.title = title
        this.content = content
        this.city = city
        commentCount = 0L
        likeCount = 0L
    }

    val writerName: String
        get() = user.getName()

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
            user: User, title: String, content: String, city: City
        ): Post {
            return Post(user, title, content, city)
        }
    }
}

