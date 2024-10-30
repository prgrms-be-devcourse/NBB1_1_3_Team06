package com.nbe3.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?
}
