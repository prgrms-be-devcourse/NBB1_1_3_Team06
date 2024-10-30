package com.nbe3.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val emergencyRoomReader: EmergencyRoomReader,
    private val fileMetaDataReader: FileMetaDataReader,
    private val userReader: UserReader,
    private val userUpdater: UserUpdater,
) {

    @jakarta.transaction.Transactional
    fun requestMedicalAuthority(userId: Long, medicalProfile: MedicalProfile) {
        val emergencyRoom = emergencyRoomReader.read(medicalProfile.emergencyRoomId)
        val license = fileMetaDataReader.read(medicalProfile.licenseId)
        val user = userReader.read(userId)
        userUpdater.requestMedicalRole(user, emergencyRoom, license)
    }

    fun getMyProfile(userId: Long): MyProfile {
        return MyProfile.from(userReader.read(userId))
    }

    fun updateProfile(userId: Long, profile: UpdateProfile) {
        val user = userReader.read(userId)
        userUpdater.update(user, profile)
    }

    fun changePassword(userId: Long, password: UpdatePassword) {
        val user = userReader.read(userId)
        userUpdater.update(user, password)
    }
}
