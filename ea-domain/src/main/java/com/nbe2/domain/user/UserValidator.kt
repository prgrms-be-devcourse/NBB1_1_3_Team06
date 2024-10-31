package com.nbe2.domain.user

import com.nbe2.domain.user.exception.AlreadyExistsEmailException
import org.springframework.stereotype.Component

@Component
class UserValidator(private val userRepository: UserRepository) {

    fun validate(email: String) {
        if (isEmailExists(email)) {
            throw AlreadyExistsEmailException
        }
    }

    fun isEmailExists(email: String) = userRepository.existsByEmail(email)
}
