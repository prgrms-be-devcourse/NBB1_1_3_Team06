package com.nbe2.domain.user

import com.nbe2.domain.emergencyroom.EmergencyRoom
import com.nbe2.domain.file.FileMetaData
import jakarta.persistence.*

@Entity
@Table(name = "medical_person_infos")
class MedicalPersonInfo
private constructor(
        @JoinColumn(name = "user_id")
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        val user: User,
        @JoinColumn(name = "emergency_room_id")
        @ManyToOne(fetch = FetchType.LAZY)
        val emergencyRoom: EmergencyRoom,
        @JoinColumn(name = "file_id")
        @OneToOne(fetch = FetchType.LAZY)
        val license: FileMetaData,
) {

    @Id
    @Column(name = "medical_person_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    companion object {
        fun of(
                user: User,
                emergencyRoom: EmergencyRoom,
                license: FileMetaData,
        ) = MedicalPersonInfo(user, emergencyRoom, license)
    }
}
