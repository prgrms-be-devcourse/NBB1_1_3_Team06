package com.nbe2.infra.auth.client

import com.nbe2.common.constants.EAConstants.BEARER
import com.nbe2.common.properties.OAuthProperty
import com.nbe2.domain.auth.OAuthClient
import com.nbe2.domain.auth.OAuthProfile
import com.nbe2.infra.kakao.client.KakaoAuthClient
import com.nbe2.infra.kakao.client.KakaoProfileClient
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class KakaoClient : OAuthClient {
    private val kakaoAuthClient: KakaoAuthClient? = null
    private val kakaoProfileClient: KakaoProfileClient? = null

    override fun getOAuthProfile(code: String): OAuthProfile {
        val kakaoTokenResponse =
            kakaoAuthClient!!.auth(OAuthProperty.CLIENT_ID, OAuthProperty.REDIRECT_URI, code)
        return kakaoProfileClient!!.getUserInfo(BEARER + kakaoTokenResponse.access_token)
    }
}
