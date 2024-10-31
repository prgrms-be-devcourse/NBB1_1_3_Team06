package com.nbe2.infra.auth.client

import com.nbe2.common.constants.EAConstants.BEARER
import com.nbe2.common.properties.OAuthProperty.Companion.CLIENT_ID
import com.nbe2.common.properties.OAuthProperty.Companion.REDIRECT_URI
import com.nbe2.domain.auth.OAuthClient
import com.nbe2.domain.auth.OAuthProfile
import com.nbe2.infra.kakao.client.KakaoAuthClient
import com.nbe2.infra.kakao.client.KakaoProfileClient
import com.nbe2.infra.kakao.dto.KakaoTokenResponse
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
