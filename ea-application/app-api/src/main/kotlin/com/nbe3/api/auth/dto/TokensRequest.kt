package com.nbe3.api.auth.dto

import com.nbe3.domain.auth.Tokens

data class TokensRequest(val accessToken: String, val refreshToken: String) {

    fun to(): Tokens {
        return Tokens(accessToken, refreshToken)
    }
}
