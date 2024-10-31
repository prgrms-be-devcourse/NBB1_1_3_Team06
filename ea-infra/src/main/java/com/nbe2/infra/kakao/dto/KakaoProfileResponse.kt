package com.nbe2.infra.kakao.dto

import com.nbe2.domain.auth.OAuthProfile

class KakaoProfileResponse(override val nickname: String, override val email: String) : OAuthProfile
