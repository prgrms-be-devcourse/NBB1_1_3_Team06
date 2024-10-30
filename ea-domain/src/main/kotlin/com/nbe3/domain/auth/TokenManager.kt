package com.nbe3.domain.auth

import com.nbe3.domain.auth.exception.RefreshNotFoundException
import org.springframework.stereotype.Component

@Component
class TokenManager(private val tokenRepository: TokenRepository) {

    fun save(refreshToken: RefreshToken) {
        tokenRepository.setRefreshToken(refreshToken)
    }

    fun removeRefreshToken(userId: Long) {
        tokenRepository.removeRefreshToken(userId)
    }

    fun checkRefreshToken(userId: Long) {
        val refreshToken = tokenRepository.getRefreshToken(userId)
        refreshToken ?: throw RefreshNotFoundException.EXCEPTION
    }
}
