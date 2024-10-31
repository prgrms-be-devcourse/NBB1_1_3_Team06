package com.nbe2.domain.emergencyroom

import jakarta.persistence.Embeddable
import java.util.*
import kotlin.math.*

@Embeddable
data class Coordinate(
    val longitude: Double?,
    val latitude: Double?
) {
    fun distanceTo(targetCoordinate: Coordinate): Double {
        val EARTH_RADIUS_KM = 6371 // 지구의 평균 반지름 (킬로미터 단위)
        val lat1 = Math.toRadians(latitude ?: 0.0)
        val lon1 = Math.toRadians(longitude ?: 0.0)
        val lat2 = Math.toRadians(targetCoordinate.latitude ?: 0.0)
        val lon2 = Math.toRadians(targetCoordinate.longitude ?: 0.0)
        val deltaLat = lat2 - lat1
        val deltaLon = lon2 - lon1
        val a = sin(deltaLat / 2).pow(2) +
                (cos(lat1) * cos(lat2) * sin(deltaLon / 2).pow(2))
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return (EARTH_RADIUS_KM * c * 100).roundToInt() / 100.0
    }

    fun convertorLatitudeAndLongitude(): String {
        return "$longitude,$latitude"
    }

    companion object {
        fun of(longitude: Double?, latitude: Double?): Coordinate {
            return Coordinate(longitude, latitude)
        }
    }
}
