package com.nbe3.domain.user

import com.nbe3.domain.auth.PasswordEncoder
import com.nbe3.domain.user.exception.InvalidPasswordException
import org.springframework.stereotype.Component

@Component
class UserUpdater(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {

    fun requestMedicalRole(user: User, emergencyRoom: EmergencyRoom, license: FileMetaData) {
        val medicalPersonInfo = MedicalPersonInfo.of(user, emergencyRoom, license)
        user.assignMedicalRole(medicalPersonInfo)
        userRepository.save(user)
    }

    fun update(user: User, profile: UpdateProfile) {
        user.update(profile)
        userRepository.save(user)
    }

    fun update(user: User, password: UpdatePassword) {
        if (passwordEncoder.isPasswordUnmatched(password.previous, user.password)) {
            throw InvalidPasswordException.EXCEPTION
        }

        user.changePassword(passwordEncoder.encode(password.toChange))
        userRepository.save(user)
    }
}
