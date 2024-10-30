package com.nbe3.domain.auth

interface PasswordEncoder {

    fun encode(plainPassword: String): String

    fun isPasswordUnmatched(plainPassword: String, encodedPassword: String): Boolean
}
