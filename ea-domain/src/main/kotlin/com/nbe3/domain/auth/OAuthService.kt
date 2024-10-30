package com.nbe3.domain.auth

import com.nbe3.domain.user.User
import com.nbe3.domain.user.UserAppender
import com.nbe3.domain.user.UserReader
import com.nbe3.domain.user.UserValidator
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val oAuthClient: OAuthClient,
    private val tokenManager: TokenManager,
    private val tokenGenerator: TokenGenerator,
    private val userValidator: UserValidator,
    private val userReader: UserReader,
    private val userAppender: UserAppender
) {

    val connectionUrl: String
        get() = oAuthClient.connectionUrl

    fun login(code: String): Tokens {
        val oAuthProfile = oAuthClient.getOAuthProfile(code)
        if (!userValidator.isEmailExists(oAuthProfile.email)) {
            userAppender.append(oAuthProfile)
        }

        val user = userReader.read(oAuthProfile.email)
        val tokens = tokenGenerator.generate(UserPrincipal.of(user.id!!, user.role))
        tokenManager.save(RefreshToken.of(user.id!!, tokens.refreshToken))

        return tokens
    }
}
