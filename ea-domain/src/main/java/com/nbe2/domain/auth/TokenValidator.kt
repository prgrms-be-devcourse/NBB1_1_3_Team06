package com.nbe2.domain.auth

interface TokenValidator {

    fun checkJwt(jwt: String): Boolean
}
