package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class Like private constructor(
    @field:JoinColumn(name = "post_id") @field:ManyToOne(fetch = FetchType.LAZY) private val post: Post,
    user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user: User = user

    companion object {
        fun create(post: Post, user: User): Like {
            return Like(post, user)
        }
    }
}
