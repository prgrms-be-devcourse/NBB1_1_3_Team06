package com.nbe2.api.auth.dto

import com.nbe2.domain.auth.Login

data class LoginRequest(val email: String, val password: String) {
    fun toLogin() = Login(email, password)
}
