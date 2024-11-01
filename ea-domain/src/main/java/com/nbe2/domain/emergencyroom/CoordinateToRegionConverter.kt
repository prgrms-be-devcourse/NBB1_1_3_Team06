package com.nbe2.domain.emergencyroom

interface CoordinateToRegionConverter {
    fun convert(coordinate: Coordinate): Region
}
