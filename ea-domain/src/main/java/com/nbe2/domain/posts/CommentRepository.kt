package com.nbe2.domain.posts

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment?, Long?> {
    @Query(
            "select c from Comment c join fetch c.user u where c.post.id = :postId"
    )
    fun findByPostId(postId: Long?): List<Comment?>?
}
