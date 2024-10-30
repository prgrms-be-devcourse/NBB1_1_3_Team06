package com.nbe2.api.global.util

import com.nbe2.domain.auth.AuthConstants
import com.nbe2.domain.auth.Tokens
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import kotlin.time.Duration
import kotlin.time.toJavaDuration
import kotlin.time.toKotlinDuration

class TokenUtils {

    companion object {
        @JvmStatic
        fun createTokenHeaders(tokens: Tokens): HttpHeaders {
            val headers = HttpHeaders()
            val cookie = createHttpOnlyCookie(
                AuthConstants.REFRESH_TOKEN_COOKIE_NAME,
                tokens.refreshToken,
                AuthConstants.REFRESH_TOKEN_TTL.toKotlinDuration()
            )
            headers[HttpHeaders.AUTHORIZATION] = tokens.accessToken
            headers[HttpHeaders.SET_COOKIE] = cookie.toString()
            return headers
        }

        private fun createHttpOnlyCookie(name: String, value: String, maxAge: Duration): ResponseCookie {
            return ResponseCookie.from(name, value).httpOnly(true).path("/").maxAge(maxAge.toJavaDuration()).build()
        }
    }
}
