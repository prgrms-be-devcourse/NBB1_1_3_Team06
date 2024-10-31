package com.nbe2.domain.auth

import kotlin.time.Duration.Companion.days

object AuthConstants {
    const val REFRESH_TOKEN_COOKIE_NAME: String = "refreshToken"

    val REFRESH_TOKEN_TTL = 14.days
}
