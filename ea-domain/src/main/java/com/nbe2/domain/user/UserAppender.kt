package com.nbe2.domain.user

import com.nbe2.domain.auth.OAuthProfile
import com.nbe2.domain.auth.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val passwordEncoder: PasswordEncoder,
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
        userRepository.save(User.of(oAuthProfile.nickname, oAuthProfile.email, ""))
    }
}
