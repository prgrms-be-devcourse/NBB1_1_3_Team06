package com.nbe2.domain.emergencyroom

import jakarta.persistence.*
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKTReader
import com.nbe2.domain.emergencyroom.exception.InvalidCoordinateException
import com.nbe2.domain.global.BaseTimeEntity

@Entity
@Table(
    name = "emergency_rooms",
    uniqueConstraints = [UniqueConstraint(columnNames = ["hospitalName", "longitude", "latitude"])]
)
data class EmergencyRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emergency_room_id")
    val id: Long? = null,

    val hpId: String,
    val hospitalName: String,
    val zipCode: String,
    val address: String,
    val mainContactNumber: String,
    val emergencyRoomContactNumber: String,
    val simpleMap: String,
    val emergencyRoomAvailability: Boolean,
    val medicalDepartments: String,

    @Column(columnDefinition = "GEOMETRY")
    val location: Point,

    @Embedded
    val bedCount: BedCount
) : BaseTimeEntity() {

    fun getLocation(): Coordinate {
        return Coordinate.of(location.x, location.y)
    }

    companion object {
        fun coordinateToPoint(coordinate: Coordinate): Point {
            val pointWKT = "POINT(${coordinate.longitude} ${coordinate.latitude})"
            return try {
                WKTReader().read(pointWKT) as Point
            } catch (e: Exception) {
                throw InvalidCoordinateException.EXCEPTION
            }
        }

        fun create(
            hpId: String,
            hospitalName: String,
            zipCode: String,
            address: String,
            mainContactNumber: String,
            emergencyRoomContactNumber: String,
            simpleMap: String,
            emergencyRoomAvailability: Boolean,
            coordinate: Coordinate,
            medicalDepartments: String,
            totalBedCount: Int,
            thoracicIcuBedCount: Int,
            neurologicalIcuBedCount: Int,
            emergencyRoomBedCount: Int,
            generalWardBedCount: Int,
            generalIcuBedCount: Int,
            neonatalIcuBedCount: Int,
            operatingRoomBedCount: Int
        ): EmergencyRoom {
            return EmergencyRoom(
                hpId = hpId,
                hospitalName = hospitalName,
                zipCode = zipCode,
                address = address,
                mainContactNumber = mainContactNumber,
                emergencyRoomContactNumber = emergencyRoomContactNumber,
                simpleMap = simpleMap,
                emergencyRoomAvailability = emergencyRoomAvailability,
                location = coordinateToPoint(coordinate),
                medicalDepartments = medicalDepartments,
                bedCount = BedCount(
                    totalBedCount = totalBedCount,
                    thoracicIcuBedCount = thoracicIcuBedCount,
                    neurologicalIcuBedCount = neurologicalIcuBedCount,
                    emergencyRoomBedCount = emergencyRoomBedCount,
                    generalWardBedCount = generalWardBedCount,
                    generalIcuBedCount = generalIcuBedCount,
                    neonatalIcuBedCount = neonatalIcuBedCount,
                    operatingRoomBedCount = operatingRoomBedCount
                )
            )
        }
    }

    @Embeddable
    data class BedCount(
        val totalBedCount: Int,
        val thoracicIcuBedCount: Int,
        val neurologicalIcuBedCount: Int,
        val emergencyRoomBedCount: Int,
        val generalWardBedCount: Int,
        val generalIcuBedCount: Int,
        val neonatalIcuBedCount: Int,
        val operatingRoomBedCount: Int
    )
}
