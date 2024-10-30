package com.nbe3.domain.auth

import com.nbe3.common.properties.OAuthProperty.Companion.BASE_URL
import com.nbe3.common.properties.OAuthProperty.Companion.CLIENT_ID
import com.nbe3.common.properties.OAuthProperty.Companion.KAKAO_OAUTH_QUERY_STRING
import com.nbe3.common.properties.OAuthProperty.Companion.REDIRECT_URI

interface OAuthClient {
    val connectionUrl: String
        get() = BASE_URL + java.lang.String.format(KAKAO_OAUTH_QUERY_STRING, CLIENT_ID, REDIRECT_URI)

    fun getOAuthProfile(code: String): OAuthProfile
}
