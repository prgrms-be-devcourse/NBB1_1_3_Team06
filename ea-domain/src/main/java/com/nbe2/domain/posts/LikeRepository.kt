package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository : JpaRepository<Like?, Long?> {
    fun findByPostAndUser(post: Post?, user: User?): Optional<Like?>

    fun deleteByPostAndUser(post: Post?, user: User?)
}
