package com.nbe2.domain.auth

import com.nbe2.domain.user.UserAppender
import com.nbe2.domain.user.UserProfile
import com.nbe2.domain.user.UserValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
        private val tokenGenerator: TokenGenerator,
        private val tokenManager: TokenManager,
        private val authenticator: Authenticator,
        private val userValidator: UserValidator,
        private val userAppender: UserAppender,
        private val tokenProvider: TokenProvider,
) {

    @Transactional
    fun signUp(userProfile: UserProfile) {
        userValidator.validate(userProfile.email)
        userAppender.append(userProfile)
    }

    fun login(login: Login): Tokens {
        val userPrincipal = authenticator.authenticate(login)
        val tokens = tokenGenerator.generate(userPrincipal)
        tokenManager.save(
                RefreshToken.Companion.of(
                        userPrincipal.userId,
                        tokens.refreshToken,
                )
        )

        return tokens
    }

    fun logout(userId: Long) {
        tokenManager.removeRefreshToken(userId)
    }

    fun updateToken(tokens: Tokens): Tokens {
        val userPrincipal =
                tokenProvider.getTokenUserPrincipal(tokens.refreshToken)
        tokenManager.checkRefreshToken(userPrincipal.userId)
        val newRefreshToken = tokenGenerator.generate(userPrincipal)
        tokenManager.save(newRefreshToken.getRefreshToken(userPrincipal.userId))

        return newRefreshToken
    }
}
