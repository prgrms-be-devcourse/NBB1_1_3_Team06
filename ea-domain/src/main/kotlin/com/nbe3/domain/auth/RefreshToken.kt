package com.nbe3.domain.auth

data class RefreshToken(val userId: Long, val refreshToken: String) {

    companion object {
        fun of(userId: Long, refreshToken: String): RefreshToken {
            return RefreshToken(userId, refreshToken)
        }
    }
}
