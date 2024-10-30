package com.nbe3.domain.auth

object AuthConstants {
    const val REFRESH_TOKEN_COOKIE_NAME: String = "refreshToken"

    val REFRESH_TOKEN_TTL: java.time.Duration = java.time.Duration.ofDays(14)
}
