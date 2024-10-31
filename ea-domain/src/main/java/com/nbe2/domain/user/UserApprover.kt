package com.nbe2.domain.user

import org.springframework.stereotype.Component

@Component
class UserApprover(private val userRepository: UserRepository) {

    fun approve(user: User) {
        user.approve()
        userRepository.save(user)
    }
}
