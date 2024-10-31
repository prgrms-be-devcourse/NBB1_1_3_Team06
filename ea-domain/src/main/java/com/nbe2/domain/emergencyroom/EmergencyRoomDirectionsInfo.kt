package com.nbe2.domain.emergencyroom

import com.fasterxml.jackson.annotation.JsonProperty

data class EmergencyRoomDirectionsInfo(
    @field:JsonProperty("code") val code: Int,
    @field:JsonProperty("message") val message: String,
    @field:JsonProperty("currentDateTime") val currentDateTime: String,
    @field:JsonProperty("route") val route: Route
) {
    data class Route(
        @JsonProperty("traoptimal") val traoptimal: List<Traoptimal>
    )

    data class Traoptimal(
        @JsonProperty("summary") val summary: Summary,
        @JsonProperty("path") val path: List<Array<Double>>,
        @JsonProperty("section") val section: List<Section>,
        @JsonProperty("guide") val guide: List<Guide>
    )

    data class Summary(
        @JsonProperty("start") val start: Start,
        @JsonProperty("goal") val goal: Goal,
        @JsonProperty("distance") val distance: Int,
        @JsonProperty("duration") val duration: Int,
        @JsonProperty("departureTime") val departureTime: String,
        @JsonProperty("bbox") val bbox: List<List<Double>>,
        @JsonProperty("tollFare") val tollFare: Int,
        @JsonProperty("taxiFare") val taxiFare: Int,
        @JsonProperty("fuelPrice") val fuelPrice: Int
    )

    data class Start(
        @JsonProperty("location") val location: List<Double>
    )

    data class Goal(
        @JsonProperty("location") val location: List<Double>,
        @JsonProperty("dir") val dir: Int
    )

    data class Section(
        @JsonProperty("pointIndex") val pointIndex: Int,
        @JsonProperty("pointCount") val pointCount: Int,
        @JsonProperty("distance") val distance: Int,
        @JsonProperty("name") val name: String,
        @JsonProperty("congestion") val congestion: Int,
        @JsonProperty("speed") val speed: Int
    )

    data class Guide(
        @JsonProperty("pointIndex") val pointIndex: Int,
        @JsonProperty("type") val type: Int,
        @JsonProperty("instructions") val instructions: String,
        @JsonProperty("distance") val distance: Int,
        @JsonProperty("duration") val duration: Int
    )
}
