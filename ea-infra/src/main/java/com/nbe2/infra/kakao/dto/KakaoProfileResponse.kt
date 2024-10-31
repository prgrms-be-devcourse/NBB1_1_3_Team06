package com.nbe2.infra.kakao.dto

import com.nbe2.domain.auth.OAuthProfile
import lombok.Getter
import lombok.NoArgsConstructor

@Getter
@NoArgsConstructor
class KakaoProfileResponse : OAuthProfile {
    private val nickname: String? = null
    private val email: String? = null

    override fun getNickname(): String? {
        return nickname
    }

    override fun getEmail(): String? {
        return email
    }
}
