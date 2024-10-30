package com.nbe3.api.auth.dto

import com.nbe3.domain.auth.Login

data class LoginRequest(val email: String, val password: String) {
    fun toLogin(): Login {
        return Login(email, password)
    }
}
