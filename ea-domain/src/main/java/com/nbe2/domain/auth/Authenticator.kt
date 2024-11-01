package com.nbe2.domain.auth

import com.nbe2.domain.auth.exception.AuthenticationException
import com.nbe2.domain.user.UserReader
import org.springframework.stereotype.Component

@Component
class Authenticator(
        private val passwordEncoder: PasswordEncoder,
        private val userReader: UserReader,
) {

    fun authenticate(login: Login): UserPrincipal {
        val user = userReader.read(login.email)
        validatePassword(login.password, user.password)

        return UserPrincipal.of(user.id!!, user.role)
    }

    private fun validatePassword(plain: String, encoded: String) {
        if (passwordEncoder.isPasswordUnmatched(plain, encoded)) {
            throw AuthenticationException
        }
    }
}
