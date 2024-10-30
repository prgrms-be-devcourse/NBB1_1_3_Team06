package com.nbe3.domain.user

import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val passwordEncode: PasswordEncoder,
    private val userRepository: UserRepository
) {

    fun append(userProfile: UserProfile) {
        userRepository.save(
            User.of(
                userProfile.name,
                userProfile.email,
                passwordEncoder.encode(userProfile.password)
            )
        )
    }

    fun append(oAuthProfile: OAuthProfile) {
        userRepository.save(User.of(oAuthProfile.getNickname(), oAuthProfile.getEmail(), ""))
    }
}
