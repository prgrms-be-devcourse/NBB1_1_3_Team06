package com.nbe2.infra.auth.client

import com.nbe2.common.constants.EAConstants.BEARER
import com.nbe2.common.properties.OAuthProperty
import com.nbe2.domain.auth.OAuthClient
import com.nbe2.domain.auth.OAuthProfile
import com.nbe2.infra.kakao.client.KakaoAuthClient
import com.nbe2.infra.kakao.client.KakaoProfileClient
import org.springframework.stereotype.Component

@Component
class KakaoClientAdapter(
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoProfileClient: KakaoProfileClient
) : OAuthClient {

    override fun getOAuthProfile(code: String): OAuthProfile {
        val kakaoTokenResponse =
            kakaoAuthClient.auth(OAuthProperty.CLIENT_ID, OAuthProperty.REDIRECT_URI, code)
        return kakaoProfileClient.getUserInfo(BEARER + kakaoTokenResponse.access_token)
    }
}
