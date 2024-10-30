package com.nbe3.infra.auth.client

import com.nbe3.common.constants.EAConstants.BEARER
import com.nbe3.common.properties.OAuthProperty.Companion.CLIENT_ID
import com.nbe3.common.properties.OAuthProperty.Companion.REDIRECT_URI
import com.nbe3.domain.auth.OAuthClient
import com.nbe3.domain.auth.OAuthProfile
import com.nbe3.infra.kakao.client.KakaoAuthClient
import com.nbe3.infra.kakao.client.KakaoProfileClient
import com.nbe3.infra.kakao.dto.KakaoTokenResponse
import org.springframework.stereotype.Component

@Component
class KakaoClient(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoProfileClient: KakaoProfileClient
) : OAuthClient {

    override fun getOAuthProfile(code: String): OAuthProfile {
        val kakaoTokenResponse: KakaoTokenResponse = kakaoAuthClient.auth(CLIENT_ID!!, REDIRECT_URI!!, code)
        return kakaoProfileClient.getUserInfo(BEARER + kakaoTokenResponse.access_token)
    }
}
