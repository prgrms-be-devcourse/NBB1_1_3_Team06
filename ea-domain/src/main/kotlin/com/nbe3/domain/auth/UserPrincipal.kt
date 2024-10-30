package com.nbe3.domain.auth

import com.nbe3.domain.user.UserRole

class UserPrincipal(val userId: Long, val role: UserRole) {

    companion object {
        fun of(userId: Long, role: UserRole): UserPrincipal {
            return UserPrincipal(userId, role)
        }
    }
}
