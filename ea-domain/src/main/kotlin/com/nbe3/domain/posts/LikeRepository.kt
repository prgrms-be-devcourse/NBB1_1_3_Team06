package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LikeRepository : JpaRepository<Like?, Long?> {
    fun findByPostAndUser(post: Post?, user: User?): Optional<Like?>

    fun deleteByPostAndUser(post: Post?, user: User?)
}
