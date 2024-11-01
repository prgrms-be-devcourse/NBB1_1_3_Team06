package com.nbe2.infra.kakao.dto

data class KakaoTokenResponse(
        val access_token: String,
        val refresh_token: String,
)
