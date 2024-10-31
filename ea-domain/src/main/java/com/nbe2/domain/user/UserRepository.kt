package com.nbe2.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?
}
