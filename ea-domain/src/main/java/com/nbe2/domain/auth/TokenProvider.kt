package com.nbe2.domain.auth

interface TokenProvider {

    fun getTokenUserPrincipal(token: String): UserPrincipal
}
