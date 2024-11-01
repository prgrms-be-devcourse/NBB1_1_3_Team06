package com.nbe2.domain.emergencyroom

import com.nbe2.domain.emergencyroom.Coordinate.Companion.of
import com.querydsl.core.annotations.QueryProjection
import org.locationtech.jts.geom.Point

data class EmergencyRoomMapInfo(
    val emergencyRoomId: Long,
    val hpId: String,
    val hospitalName: String,
    val address: String,
    val simpleMap: String,
    val coordinate: Coordinate,
    val distance: Double
) {
    @QueryProjection
    constructor(
        emergencyRoomId: Long,
        hpId: String,
        hospitalName: String,
        address: String,
        simpleMap: String,
        location: Point,
        distance: Double
    ) : this(
        emergencyRoomId,
        hpId,
        hospitalName,
        address,
        simpleMap,
        of(location.x, location.y),
        distance
    )
}
