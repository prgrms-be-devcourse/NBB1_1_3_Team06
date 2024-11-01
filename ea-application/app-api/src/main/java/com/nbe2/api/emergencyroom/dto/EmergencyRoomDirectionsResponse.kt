package com.nbe2.api.emergencyroom.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo

data class EmergencyRoomDirectionsResponse(
        @JsonProperty("code") val code: Int,
        @JsonProperty("message") val message: String,
        @JsonProperty("currentDateTime") val currentDateTime: String,
        @JsonProperty("route") val route: Route,
) {
    data class Route(
            @JsonProperty("traoptimal") val traoptimal: List<Traoptimal>
    )

    data class Traoptimal(
            @JsonProperty("summary") val summary: Summary?,
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
        fun to(
                info: EmergencyRoomDirectionsInfo
        ): EmergencyRoomDirectionsResponse {
            return EmergencyRoomDirectionsResponse(
                    code = info.code,
                    message = info.message,
                    currentDateTime = info.currentDateTime,
                    route = convertRoute(info.route),
            )
        }

        private fun convertRoute(
                route: EmergencyRoomDirectionsInfo.Route
        ): Route {
            val traoptimalList = route.traoptimal.map { convertTraoptimal(it) }
            return Route(traoptimalList)
        }

        private fun convertTraoptimal(
                traoptimal: EmergencyRoomDirectionsInfo.Traoptimal
        ): Traoptimal {
            return Traoptimal(
                    summary = convertSummary(traoptimal.summary),
                    path = traoptimal.path,
                    section = traoptimal.section.map { convertSection(it) },
                    guide = traoptimal.guide.map { convertGuide(it) },
            )
        }

        private fun convertSummary(
                summary: EmergencyRoomDirectionsInfo.Summary?
        ): Summary? {
            return summary?.let {
                Summary(
                        start = convertStart(it.start),
                        goal = convertGoal(it.goal),
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
                section: EmergencyRoomDirectionsInfo.Section
        ): Section {
            return Section(
                    pointIndex = section.pointIndex,
                    pointCount = section.pointCount,
                    distance = section.distance,
                    name = section.name,
                    congestion = section.congestion,
                    speed = section.speed,
            )
        }

        private fun convertGuide(
                guide: EmergencyRoomDirectionsInfo.Guide
        ): Guide {
            return Guide(
                    pointIndex = guide.pointIndex,
                    type = guide.type,
                    instructions = guide.instructions,
                    distance = guide.distance,
                    duration = guide.duration,
            )
        }

        private fun convertGoal(goal: EmergencyRoomDirectionsInfo.Goal): Goal {
            return Goal(location = goal.location, dir = goal.dir)
        }

        private fun convertStart(
                start: EmergencyRoomDirectionsInfo.Start
        ): Start {
            return Start(location = start.location)
        }
    }
}
