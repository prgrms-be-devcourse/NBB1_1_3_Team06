package com.nbe2.api.auth.dto

data class OAuthConnectionResponse(val url: String) {

    companion object {
        fun from(url: String) = OAuthConnectionResponse(url)
    }
}
