package com.nbe3.domain.posts

import com.nbe3.domain.global.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment private constructor(post: Post, user: User, content: String) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    open var post: Post = post

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    open val user: User = user

    open var content: String = content

    init {
        setPost(post)
        this.user = user
        this.content = content
    }

    val postId: Long?
        get() = post!!.id

    val writerId: Long
        get() = user.getId()

    val writerName: String
        get() = user.getName()

    // ** 연관관계 편의 메서드**//
    private fun setPost(post: Post) {
        this.post = post
        post.addComment(this)
    }

    // ** business logic **//
    fun update(content: String): Long? {
        this.content = content
        return id
    }

    companion object {
        fun create(post: Post, user: User, content: String): Comment {
            return Comment(post, user, content)
        }
    }
}
