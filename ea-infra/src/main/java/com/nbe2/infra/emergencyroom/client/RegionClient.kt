package com.nbe2.infra.emergencyroom.client

import com.nbe2.domain.emergencyroom.Coordinate
import com.nbe2.domain.emergencyroom.CoordinateToRegionConverter
import com.nbe2.domain.emergencyroom.Region
import com.nbe2.infra.emergencyroom.exception.RegionNotFoundException
import com.nbe2.infra.kakao.client.KakaoClient
import com.nbe2.infra.kakao.dto.KakaoRegionResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class RegionClient(
    private val kakaoClient: KakaoClient
) : CoordinateToRegionConverter{

    override fun convert(coordinate: Coordinate): Region {
        return kakaoClient
            .getRegionData(coordinate.longitude, coordinate.latitude)
            .documents
            .stream()
            .filter { obj: KakaoRegionResponse -> obj.isB }
            .map { obj: KakaoRegionResponse -> obj.toRegion() }
            .findFirst()
            .orElseThrow { RegionNotFoundException }
    }
}
