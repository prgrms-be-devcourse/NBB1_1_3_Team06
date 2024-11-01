package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.exception.EmergencyRoomNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class EmergencyRoomReaderTest {
    @InjectMocks
    private val emergencyRoomReader: EmergencyRoomReader? = null

    @Mock
    private val emergencyRoomRepository: EmergencyRoomRepository? = null

    @Nested
    @DisplayName("ID로 응급실을 조회한다.")
    internal inner class ReadByIdTest {
        @Test
        @DisplayName("유효한 ID 전달 시 응급실을 반환한다.")
        fun givenEmergencyRoomId_whenEmergencyRoomExists_thenShouldReturnEmergencyRoom() {
            // given
            val emergencyRoomId = 1L
            val expected = EmergencyRoomFixture.create()

            // when
            Mockito.`when`(emergencyRoomRepository!!.findById(emergencyRoomId))
                .thenReturn(Optional.of(expected!!))

            // then
            val actual = emergencyRoomReader!!.read(emergencyRoomId)
            Assertions.assertEquals(expected, actual)
        }

        @Test
        @DisplayName("존재하지 않는 응급실 조회 시 예외가 발생한다.")
        fun givenEmergencyRoomId_whenEmergencyRoomNotExists_thenShouldThrowException() {
            // given
            val emergencyRoomId = 1L

            // when
            Mockito.`when`(emergencyRoomRepository!!.findById(emergencyRoomId)).thenReturn(Optional.empty())

            // then
            Assertions.assertThrows(
                EmergencyRoomNotFoundException::class.java
            ) { emergencyRoomReader!!.read(emergencyRoomId) }
        }
    }

    @Nested
    @DisplayName("병원 ID로 응급실을 조회한다.")
    internal inner class ReadByHospitalIdTest {
        @Test
        @DisplayName("유효한 병원 ID 전달 시 응급실을 반환한다.")
        fun givenHospitalId_whenEmergencyRoomExists_thenShouldReturnEmergencyRoom() {
            // given
            val hospitalId = EmergencyRoomFixture.HP_ID
            val expected = EmergencyRoomFixture.create()

            // when
            Mockito.`when`<Optional<EmergencyRoom?>>(emergencyRoomRepository!!.findByHpId(hospitalId))
                .thenReturn(
                    Optional.of(
                        expected!!
                    )
                )

            // then
            val actual = emergencyRoomReader!!.read(hospitalId)
            Assertions.assertEquals(expected, actual)
        }

        @Test
        @DisplayName("존재하지 않는 병원 ID로 조회 시 예외가 발생한다.")
        fun givenHospitalId_whenEmergencyRoomNotExists_thenShouldThrowException() {
            // given
            val hospitalId = "HP999"

            // when
            Mockito.`when`(emergencyRoomRepository!!.findByHpId(hospitalId)).thenReturn(Optional.empty())

            // then
            Assertions.assertThrows(
                EmergencyRoomNotFoundException::class.java
            ) { emergencyRoomReader!!.read(hospitalId) }
        }
    }

    @Nested
    @DisplayName("병원 이름으로 응급실 ID 목록을 조회한다.")
    internal inner class ReadByHospitalNameTest {
        @Test
        @DisplayName("병원 이름에 해당하는 응급실 ID 목록을 반환한다.")
        fun givenHospitalName_whenEmergencyRoomsExist_thenShouldReturnListOfHpIds() {
            // given
            val hospitalName = EmergencyRoomFixture.HOSPITAL_NAME
            val expected = java.util.List.of(EmergencyRoomFixture.HP_ID, "HP002")

            // when
            Mockito.`when`<List<EmergencyRoom?>>(
                emergencyRoomRepository!!.findByHospitalNameContaining(
                    hospitalName
                )
            )
                .thenReturn(Collections.nCopies(2, EmergencyRoomFixture.create()))

            // then
            val actual = emergencyRoomReader!!.readByHospitalName(hospitalName)
            Assertions.assertEquals(expected.size, actual.size)
        }

        @Test
        @DisplayName("해당 이름의 병원이 없을 경우 빈 목록을 반환한다.")
        fun givenHospitalName_whenNoEmergencyRoomsExist_thenShouldReturnEmptyList() {
            // given
            val hospitalName = "없는병원"

            // when
            Mockito.`when`(emergencyRoomRepository!!.findByHospitalNameContaining(hospitalName))
                .thenReturn(emptyList())

            // then
            val actual = emergencyRoomReader!!.readByHospitalName(hospitalName)
            Assertions.assertTrue(actual.isEmpty())
        }
    }

    @Nested
    @DisplayName("병원 이름으로 좌표를 조회한다.")
    internal inner class FindByHospitalNameTest {
        @Test
        @DisplayName("유효한 병원 이름 전달 시 좌표를 반환한다.")
        fun givenHospitalName_whenEmergencyRoomExists_thenShouldReturnCoordinates() {
            // given
            val hospitalName = EmergencyRoomFixture.HOSPITAL_NAME
            val expected = EmergencyRoomFixture.COORDINATE

            // when
            Mockito.`when`<Optional<EmergencyRoom?>>(emergencyRoomRepository!!.findByHospitalName(hospitalName))
                .thenReturn(Optional.of(EmergencyRoomFixture.create()))

            // then
            val actual = emergencyRoomReader!!.readCoordinate(hospitalName)
            Assertions.assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("좌표와 거리로 응급실 정보를 조회한다.")
    internal inner class ReadByCoordinateAndDistanceTest {
        @Test
        @DisplayName("유효한 좌표와 거리 전달 시 응급실 목록을 반환한다.")
        fun givenCoordinateAndDistance_whenEmergencyRoomsExist_thenShouldReturnListOfEmergencyRooms() {
            // given
            val coordinate = EmergencyRoomFixture.COORDINATE
            val distance = EmergencyRoomFixture.DISTANCE
            val expected = java.util.List.of(EmergencyRoomFixture.createMapInfo())

            // when
            Mockito.`when`(
                emergencyRoomRepository!!.findByCoordinateAndDistance(
                    ArgumentMatchers.any(Coordinate::class.java), ArgumentMatchers.anyDouble()
                )
            )
                .thenReturn(expected)

            // then
            val actual = emergencyRoomReader!!.read(coordinate, distance)
            Assertions.assertEquals(expected.size, actual.size)
        }

        @Test
        @DisplayName("해당 좌표와 거리 내에 응급실이 없을 경우 빈 목록을 반환한다.")
        fun givenCoordinateAndDistance_whenNoEmergencyRoomsExist_thenShouldReturnEmptyList() {
            // given
            val coordinate = EmergencyRoomFixture.COORDINATE
            val distance = EmergencyRoomFixture.DISTANCE

            // when
            Mockito.`when`(
                emergencyRoomRepository!!.findByCoordinateAndDistance(
                    ArgumentMatchers.any(Coordinate::class.java), ArgumentMatchers.anyDouble()
                )
            )
                .thenReturn(emptyList())

            // then
            val actual = emergencyRoomReader!!.read(coordinate, distance)
            Assertions.assertTrue(actual.isEmpty())
        }
    }
}
