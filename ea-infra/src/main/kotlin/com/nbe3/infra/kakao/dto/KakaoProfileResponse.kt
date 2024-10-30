package com.nbe3.infra.kakao.dto

import com.nbe3.domain.auth.OAuthProfile

class KakaoProfileResponse(override val nickname: String, override val email: String) : OAuthProfile
