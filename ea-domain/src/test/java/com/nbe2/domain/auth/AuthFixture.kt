package com.nbe2.domain.auth

import com.nbe2.domain.global.EMAIL
import com.nbe2.domain.global.NAME

fun createOAuthProfile(): OAuthProfile {
    return OAuthProfileFixture()
}

class OAuthProfileFixture : OAuthProfile {
    override val nickname: String = NAME
    override val email: String = EMAIL
}
