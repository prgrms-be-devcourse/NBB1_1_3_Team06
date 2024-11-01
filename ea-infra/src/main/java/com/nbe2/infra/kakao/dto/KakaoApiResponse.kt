package com.nbe2.infra.kakao.dto

data class KakaoApiResponse<T>(val documents: List<T>)
