package com.nbe2.domain.auth

interface TokenGenerator {

    fun generate(principal: UserPrincipal): Tokens
}
