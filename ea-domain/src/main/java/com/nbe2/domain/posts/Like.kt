package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class Like private constructor(
    @field:JoinColumn(name = "post_id")
    @field:ManyToOne(fetch = FetchType.LAZY)
    private val post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private var id: Long? = null

    companion object {
        fun create(post: Post, user: User): Like {
            return Like(post, user)
        }
    }
}
