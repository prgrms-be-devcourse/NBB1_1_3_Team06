package com.nbe2.api.auth.dto

import com.nbe2.domain.auth.Tokens

data class TokensRequest(val accessToken: String, val refreshToken: String) {

    fun to() = Tokens(accessToken, refreshToken)
}
