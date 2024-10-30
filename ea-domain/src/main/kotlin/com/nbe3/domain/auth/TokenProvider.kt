package com.nbe3.domain.auth

interface TokenProvider {

    fun getTokenUserPrincipal(token: String): UserPrincipal
}
