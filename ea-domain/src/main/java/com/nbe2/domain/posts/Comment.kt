package com.nbe2.domain.posts

import com.nbe2.domain.global.BaseTimeEntity
import com.nbe2.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment private constructor(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    var content: String
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var id: Long? = null

    init {
        associateWithPost(post)
    }

    val postId: Long?
        get() = post.id

    val writerId: Long
        get() = user.id

    val writerName: String
        get() = user.name

    // ** 연관관계 편의 메서드**//
    private fun associateWithPost(post: Post) {
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
