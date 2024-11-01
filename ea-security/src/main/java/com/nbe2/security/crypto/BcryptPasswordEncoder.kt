package com.nbe2.security.crypto

import com.nbe2.domain.auth.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BcryptPasswordEncoder(
        private val passwordEncoder: BCryptPasswordEncoder
) : PasswordEncoder {

    override fun encode(plainPassword: String): String {
        return passwordEncoder.encode(plainPassword)
    }

    override fun isPasswordUnmatched(
            plainPassword: String,
            encodedPassword: String,
    ): Boolean {
        return !passwordEncoder.matches(plainPassword, encodedPassword)
    }
}
