package com.nbe2.domain.emergencyroom

import org.springframework.stereotype.Component

@Component
class EmergencyRoomDirections(private val roomClient: EmergencyRoomClient) {
    fun directionsEmergencyRoom(
            myLocation: String,
            latitudeAndLongitude: String,
    ): EmergencyRoomDirectionsInfo {
        return roomClient.directionsEmergencyRoom(
                myLocation,
                latitudeAndLongitude,
        )
    }
}
