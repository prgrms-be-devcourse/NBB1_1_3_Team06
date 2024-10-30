package com.nbe3.domain.auth

interface TokenValidator {

    fun checkJwt(jwt: String): Boolean
}
