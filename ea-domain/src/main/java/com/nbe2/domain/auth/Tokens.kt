package com.nbe2.domain.auth

data class Tokens(val accessToken: String, val refreshToken: String) {

    fun getRefreshToken(userId: Long) = RefreshToken.of(userId, refreshToken)
}
