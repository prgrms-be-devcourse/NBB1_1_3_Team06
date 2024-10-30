package com.nbe3.domain.user

import com.nbe3.domain.user.exception.AlreadyExistsEmailException
import org.springframework.stereotype.Component

@Component
class UserValidator(private val userRepository: UserRepository) {

    fun validate(email: String) {
        if (isEmailExists(email)) {
            throw AlreadyExistsEmailException.EXCEPTION
        }
    }

    fun isEmailExists(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}
