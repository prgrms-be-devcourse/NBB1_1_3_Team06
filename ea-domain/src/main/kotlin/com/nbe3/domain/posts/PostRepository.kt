package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import jakarta.persistence.LockModeType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository : JpaRepository<Post?, Long?> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Post p where p.id = :postId")
    fun findByIdWithPessimisticWriteLock(postId: Long?): Optional<Post?>?

    @Query("select distinct post " +
            "from Post post " +
            "join fetch post.user " +
            "left join fetch " +
            "post.postFiles postFiles " +
            "where post.id = :postId")
    fun findDetailById(postId: Long?): Optional<Post?>?

    @EntityGraph(attributePaths = ["user"])
    @Query("select p from Post p")
    fun findByCity(city: City?, pageable: Pageable?): Page<Post?>?

    @EntityGraph(attributePaths = ["user"])
    @Query("select p from Post p")
    fun findByUser(user: User?, pageable: Pageable?): Page<Post?>?
}
