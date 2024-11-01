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
internal class DistanceCalculatorTest {
    @InjectMocks
    private val distanceCalculator: DistanceCalculator? = null

    @Mock
    private val emergencyRoomReader: EmergencyRoomReader? = null

    @Nested
    @DisplayName("거리를 계산한다")
    internal inner class CalculateDistanceTest {
        @Test
        @DisplayName("실시간 응급실 정보를 전달하면 현재 위치로부터 거리를 계산하고 정렬한다")
        fun givenRealTimeEmergencyRoomInfos_whenCalculate_thenReturnSortedByDistance() {
            // given
            val realTimeEmergencyRoomInfos =
                EmergencyRoomFixture.createRealTimeInfoList()

            val emergencyRoom1 = EmergencyRoomFixture.create()
            val emergencyRoom2 = EmergencyRoomFixture.create()

            Mockito.`when`(emergencyRoomReader!!.read(realTimeEmergencyRoomInfos[0]!!.hpId))
                .thenReturn(emergencyRoom1)
            Mockito.`when`(emergencyRoomReader.read(realTimeEmergencyRoomInfos[1]!!.hpId))
                .thenReturn(emergencyRoom2)

            // when
            val result =
                distanceCalculator!!.calculate(realTimeEmergencyRoomInfos, EmergencyRoomFixture.COORDINATE)

            // then
            Assertions.assertEquals(2, result.size)
            Assertions.assertEquals(result[0].distance, 0.0)
            Assertions.assertTrue(result[0].distance <= result[1].distance) // 정렬된 결과인지 확인
        }

        @Test
        @DisplayName("빈 리스트를 전달하면 빈 결과를 반환한다")
        fun givenEmptyList_whenCalculate_thenReturnEmptyList() {
            // given
            val realTimeEmergencyRoomInfos = listOf<RealTimeEmergencyRoomInfo>()
            val currentCoordinate = Coordinate.of(126.9784, 37.5665)

            // when
            val result =
                distanceCalculator!!.calculate(realTimeEmergencyRoomInfos, currentCoordinate)

            // then
            Assertions.assertTrue(result.isEmpty())
        }
    }
}
