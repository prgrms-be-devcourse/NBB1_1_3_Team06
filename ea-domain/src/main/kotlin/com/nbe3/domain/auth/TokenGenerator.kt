package com.nbe3.domain.auth

interface TokenGenerator {

    fun generate(principal: UserPrincipal): Tokens
}
