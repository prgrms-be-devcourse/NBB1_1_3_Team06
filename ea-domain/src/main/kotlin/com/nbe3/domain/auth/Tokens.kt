package com.nbe3.domain.auth

data class Tokens(val accessToken: String, val refreshToken: String) {

    fun getRefreshToken(userId: Long): RefreshToken {
        return RefreshToken.of(userId, refreshToken)
    }
}
