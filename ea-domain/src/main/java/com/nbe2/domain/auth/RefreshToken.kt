package com.nbe2.domain.auth

data class RefreshToken(val userId: Long, val refreshToken: String) {

    companion object {
        fun of(userId: Long, refreshToken: String) = RefreshToken(userId, refreshToken)
    }
}
