package com.nbe2.domain.user

data class UserProfile(val name: String, val email: String, val password: String) {

    companion object {
        fun of(name: String, email: String, password: String) = UserProfile(name, email, password)
    }
}
