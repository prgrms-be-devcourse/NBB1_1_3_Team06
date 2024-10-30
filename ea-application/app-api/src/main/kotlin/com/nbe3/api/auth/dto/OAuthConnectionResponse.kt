package com.nbe3.api.auth.dto

data class OAuthConnectionResponse(val url: String) {

    companion object {
        fun from(url: String): OAuthConnectionResponse {
            return OAuthConnectionResponse(url)
        }
    }
}
