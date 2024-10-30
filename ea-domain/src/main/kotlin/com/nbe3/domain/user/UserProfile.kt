package com.nbe3.domain.user

data class UserProfile(val name: String, val email: String, val password: String) {

    companion object {
        fun of(name: String, email: String, password: String): UserProfile {
            return UserProfile(name, email, password)
        }
    }
}
