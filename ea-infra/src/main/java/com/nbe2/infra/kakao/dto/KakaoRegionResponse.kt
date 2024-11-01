package com.nbe2.infra.kakao.dto

import com.nbe2.domain.emergencyroom.Region

data class KakaoRegionResponse(
        val region_type: String,
        val address_name: String,
        val region_1depth_name: String,
        val region_2depth_name: String,
        val region_3depth_name: String,
        val region_4depth_name: String,
        val code: String,
        val x: Double,
        val y: Double,
) {
    val isB: Boolean
        // 법정동
        get() = region_type == "B"

    fun toRegion(): Region {
        return Region(region_1depth_name, region_2depth_name)
    }
}
