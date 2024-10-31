package com.nbe2.domain.auth

import com.nbe2.domain.user.UserRole

class UserPrincipal(val userId: Long, val role: UserRole) {

    companion object {
        @JvmStatic
        fun of(userId: Long, role: UserRole) = UserPrincipal(userId, role)
    }
}
