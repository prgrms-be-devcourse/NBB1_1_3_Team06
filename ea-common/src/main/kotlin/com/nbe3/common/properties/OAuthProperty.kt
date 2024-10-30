package com.nbe3.common.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OAuthProperty {
    @Value("\${oauth.kakao.base-url}")
    fun setBaseUrl(baseUrl: String?) {
        BASE_URL = baseUrl
    }

    @Value("\${oauth.kakao.client-id}")
    fun setClientId(clientId: String?) {
        CLIENT_ID = clientId
    }

    @Value("\${oauth.kakao.redirect-url}")
    fun setRedirectUri(redirectUri: String?) {
        REDIRECT_URI = redirectUri
    }

    companion object {
        var BASE_URL: String? = null
        var CLIENT_ID: String? = null
        var REDIRECT_URI: String? = null

        const val KAKAO_OAUTH_QUERY_STRING: String = "/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s"
    }
}
