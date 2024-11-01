package com.nbe2.domain.emergencyroom

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class RealTimeEmergencyRoomInfoFetcherTest {
    @InjectMocks
    private val realTimeEmergencyRoomInfoFetcher:
            RealTimeEmergencyRoomInfoFetcher? =
            null

    @Mock private val realTimeClient: EmergencyRoomClient? = null

    @Mock
    private val cacheManager: RealTimeEmergencyRoomInfoCacheManager? = null

    @Mock
    private val coordinateToRegionConverter: CoordinateToRegionConverter? = null

    @Nested
    @DisplayName("실시간 응급실 정보를 조회한다.")
    internal inner class FetchRealTimeInfoTest {
        @Test
        @DisplayName("유효한 지역을 전달하면 실시간 응급실 정보를 반환하고 캐싱한다.")
        fun givenCoordate_whenConvertRegion_FetchRealTimeInfo_thenReturnAndCacheInfo() {
            // given
            val coordinate = EmergencyRoomFixture.COORDINATE
            val expectedInfo = EmergencyRoomFixture.createRealTimeInfoList()
            val expectedRegion = EmergencyRoomFixture.getRegion()

            // when
            Mockito.`when`(coordinateToRegionConverter!!.convert(coordinate!!))
                    .thenReturn(expectedRegion)
            Mockito.`when`<List<RealTimeEmergencyRoomInfo?>>(
                            realTimeClient!!.getRealTimeInfo(expectedRegion)
                    )
                    .thenReturn(expectedInfo)

            // then
            val actualInfo =
                    realTimeEmergencyRoomInfoFetcher!!.fetch(coordinate)
            Assertions.assertEquals(expectedInfo, actualInfo)
            Mockito.verify(realTimeClient, Mockito.times(1))
                    .getRealTimeInfo(expectedRegion)
            Mockito.verify(cacheManager, Mockito.times(1)).cache(expectedInfo)
        }

        @Test
        @DisplayName("캐시 매니저가 호출되면 캐싱 로직이 동작한다.")
        fun givenCoordinate_RealTimeInfo_whenConvertRegion_CacheManagerCalled_thenCacheInfo() {
            // given
            val coordinate = EmergencyRoomFixture.COORDINATE
            val expectedRegion = EmergencyRoomFixture.getRegion()
            val realTimeInfo = EmergencyRoomFixture.createRealTimeInfoList()

            // when
            Mockito.`when`(coordinateToRegionConverter!!.convert(coordinate!!))
                    .thenReturn(expectedRegion)
            Mockito.`when`<List<RealTimeEmergencyRoomInfo?>>(
                            realTimeClient!!.getRealTimeInfo(expectedRegion)
                    )
                    .thenReturn(realTimeInfo)
            Mockito.doNothing().`when`(cacheManager).cache(realTimeInfo)

            // then
            realTimeEmergencyRoomInfoFetcher!!.fetch(coordinate)
            Mockito.verify(cacheManager, Mockito.times(1)).cache(realTimeInfo)
        }
    }
}
