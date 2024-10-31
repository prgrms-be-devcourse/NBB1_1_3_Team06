package com.nbe2.domain.emergencyroom

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmergencyRoomRepository : JpaRepository<EmergencyRoom, Long>, EmergencyRoomRepositoryCustom {
    fun findByHospitalNameContaining(name: String): List<EmergencyRoom>
    fun findByHpId(hpId: String): Optional<EmergencyRoom>
    fun findByHospitalName(hospitalName: String): Optional<EmergencyRoom>
}
