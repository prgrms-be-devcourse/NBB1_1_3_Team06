package com.nbe3.domain.auth

interface TokenRepository {

    fun setRefreshToken(refreshToken: RefreshToken)

    fun removeRefreshToken(userId: Long)

    fun getRefreshToken(userId: Long): RefreshToken?
}
