package com.nbe2.common.properties

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OAuthProperty {
    @Value("\${oauth.kakao.base-url}") lateinit var baseUrl: String

    @Value("\${oauth.kakao.client-id}") lateinit var clientId: String

    @Value("\${oauth.kakao.redirect-url}") lateinit var redirectUri: String

    companion object {
        var BASE_URL: String = ""
        var CLIENT_ID: String = ""
        var REDIRECT_URI: String = ""

        const val KAKAO_OAUTH_QUERY_STRING: String =
                "/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s"
    }

    @PostConstruct
    fun init() {
        BASE_URL = baseUrl
        CLIENT_ID = clientId
        REDIRECT_URI = redirectUri
    }
}
