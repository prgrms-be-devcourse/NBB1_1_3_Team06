package com.nbe2.infra.naver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo

data class NaverDirectionsResponse(
        @JsonProperty("code") val code: Int,
        @JsonProperty("message") val message: String,
        @JsonProperty("currentDateTime") val currentDateTime: String,
        @JsonProperty("route") val route: Route,
) {
    data class Route(
            @JsonProperty("traoptimal") val traoptimal: List<Traoptimal>
    )

    data class Traoptimal(
            @JsonProperty("summary") val summary: Summary,
            @JsonProperty("path") val path: List<Array<Double>>,
            @JsonProperty("section") val section: List<Section>,
            @JsonProperty("guide") val guide: List<Guide>,
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
            @JsonProperty("fuelPrice") val fuelPrice: Int,
    )

    data class Start(@JsonProperty("location") val location: List<Double>)

    data class Goal(
            @JsonProperty("location") val location: List<Double>,
            @JsonProperty("dir") val dir: Int,
    )

    data class Section(
            @JsonProperty("pointIndex") val pointIndex: Int,
            @JsonProperty("pointCount") val pointCount: Int,
            @JsonProperty("distance") val distance: Int,
            @JsonProperty("name") val name: String,
            @JsonProperty("congestion") val congestion: Int,
            @JsonProperty("speed") val speed: Int,
    )

    data class Guide(
            @JsonProperty("pointIndex") val pointIndex: Int,
            @JsonProperty("type") val type: Int,
            @JsonProperty("instructions") val instructions: String,
            @JsonProperty("distance") val distance: Int,
            @JsonProperty("duration") val duration: Int,
    )

    companion object {
        fun form(n: NaverDirectionsResponse): EmergencyRoomDirectionsInfo {
            return EmergencyRoomDirectionsInfo(
                    code = n.code,
                    message = n.message,
                    currentDateTime = n.currentDateTime,
                    route = convertRoute(n.route),
            )
        }

        private fun convertRoute(
                route: Route
        ): EmergencyRoomDirectionsInfo.Route {
            val traoptimalList = route.traoptimal.map { convertTraoptimal(it) }
            return EmergencyRoomDirectionsInfo.Route(traoptimalList)
        }

        private fun convertTraoptimal(
                traoptimal: Traoptimal
        ): EmergencyRoomDirectionsInfo.Traoptimal {
            return EmergencyRoomDirectionsInfo.Traoptimal(
                    summary = convertSummary(traoptimal.summary),
                    path = traoptimal.path,
                    section = traoptimal.section.map { convertSection(it) },
                    guide = traoptimal.guide.map { convertGuide(it) },
            )
        }

        private fun convertSummary(
                summary: Summary
        ): EmergencyRoomDirectionsInfo.Summary {
            return summary.let {
                EmergencyRoomDirectionsInfo.Summary(
                        start =
                                EmergencyRoomDirectionsInfo.Start(
                                        it.start.location
                                ),
                        goal =
                                EmergencyRoomDirectionsInfo.Goal(
                                        it.goal.location,
                                        it.goal.dir,
                                ),
                        distance = it.distance,
                        duration = it.duration,
                        departureTime = it.departureTime,
                        bbox = it.bbox,
                        tollFare = it.tollFare,
                        taxiFare = it.taxiFare,
                        fuelPrice = it.fuelPrice,
                )
            }
        }

        private fun convertSection(
                section: Section
        ): EmergencyRoomDirectionsInfo.Section {
            return EmergencyRoomDirectionsInfo.Section(
                    pointIndex = section.pointIndex,
                    pointCount = section.pointCount,
                    distance = section.distance,
                    name = section.name,
                    congestion = section.congestion,
                    speed = section.speed,
            )
        }

        private fun convertGuide(
                guide: Guide
        ): EmergencyRoomDirectionsInfo.Guide {
            return EmergencyRoomDirectionsInfo.Guide(
                    pointIndex = guide.pointIndex,
                    type = guide.type,
                    instructions = guide.instructions,
                    distance = guide.distance,
                    duration = guide.duration,
            )
        }
    }
}
