package com.nbe3.infra.auth.cache

import com.nbe3.domain.auth.AuthConstants
import com.nbe3.domain.auth.RefreshToken
import com.nbe3.domain.auth.TokenRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import kotlin.time.toJavaDuration

@Repository
class RedisTokenRepository(private val template: RedisTemplate<String, RefreshToken>) : TokenRepository {

    override fun setRefreshToken(refreshToken: RefreshToken) {
        template.opsForValue()
            .set(getKey(refreshToken.userId), refreshToken, AuthConstants.REFRESH_TOKEN_TTL.toJavaDuration())
    }

    override fun removeRefreshToken(userId: Long) {
        template.delete(getKey(userId))
    }

    override fun getRefreshToken(userId: Long): RefreshToken? {
        return template.opsForValue().get(getKey(userId))
    }

    private fun getKey(userId: Long): String {
        return "REFRESH_TOKEN_ER:$userId"
    }
}
