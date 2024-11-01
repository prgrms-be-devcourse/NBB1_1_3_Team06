package com.nbe2.domain.emergencyroom

import jakarta.persistence.Embeddable
import java.util.*
import kotlin.math.*

private const val earthRadiusKm = 6371.0

@Embeddable
data class Coordinate(
    val longitude: Double,
    val latitude: Double
) {
    fun distanceTo(targetCoordinate: Coordinate): Double {
        val lat1 = Math.toRadians(latitude)
        val lon1 = Math.toRadians(longitude)
        val lat2 = Math.toRadians(targetCoordinate.latitude)
        val lon2 = Math.toRadians(targetCoordinate.longitude)
        val deltaLat = lat2 - lat1
        val deltaLon = lon2 - lon1
        val a = sin(deltaLat / 2).pow(2) +
                (cos(lat1) * cos(lat2) * sin(deltaLon / 2).pow(2))
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return (earthRadiusKm * c * 100).roundToInt() / 100.0
    }

    fun convertorLatitudeAndLongitude(): String {
        return "$longitude,$latitude"
    }

    companion object {
        fun of(longitude: Double, latitude: Double): Coordinate {
            return Coordinate(longitude, latitude)
        }
    }
}
