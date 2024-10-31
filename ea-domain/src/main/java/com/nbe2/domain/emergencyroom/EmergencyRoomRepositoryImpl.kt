package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.EmergencyRoom.Companion.coordinateToPoint
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.NumberTemplate
import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository

@Repository
@RequiredArgsConstructor
class EmergencyRoomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : EmergencyRoomRepositoryCustom {
    override fun findByCoordinateAndDistance(
        coordinate: Coordinate, distance: Double
    ): List<EmergencyRoomMapInfo> {
        val calculatedDistance = getCalculatedDistance(coordinate)
        return queryFactory
            .select(
                QEmergencyRoomMapInfo(
                    QEmergencyRoom.emergencyRoom.id,  // 필요한 필드만 선택
                    QEmergencyRoom.emergencyRoom.hpId,
                    QEmergencyRoom.emergencyRoom.hospitalName,
                    QEmergencyRoom.emergencyRoom.address,
                    QEmergencyRoom.emergencyRoom.simpleMap,
                    QEmergencyRoom.emergencyRoom.location,
                    calculatedDistance
                )
            )
            .from(QEmergencyRoom.emergencyRoom)
            .where(calculatedDistance.loe(distance))
            .orderBy(calculatedDistance.asc())
            .fetch()
    }

    companion object {
        private fun getCalculatedDistance(coordinate: Coordinate): NumberTemplate<Double> {
            return Expressions.numberTemplate(
                Double::class.java,
                "ST_Distance_Sphere({0}, {1})",
                QEmergencyRoom.emergencyRoom.location,
                coordinateToPoint(coordinate)
            )
        }
    }
}
