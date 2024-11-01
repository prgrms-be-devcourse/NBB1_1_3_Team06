package com.nbe2.security.utils

import com.nbe2.domain.auth.TokenGenerator
import com.nbe2.domain.auth.Tokens
import com.nbe2.domain.auth.UserPrincipal
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtGenerator(
        @Value("\${jwt.secret-key}") private val secretKey: String,
        @Value("\${jwt.refresh-expiration-time}")
        private val refreshExpirationTime: Long,
        @Value("\${jwt.access-expiration-time}")
        private val accessExpirationTime: Long,
) : TokenGenerator {

    private val key: Key =
            SecretKeySpec(
                    secretKey.toByteArray(),
                    SignatureAlgorithm.HS256.jcaName,
            )

    // JWT 생성
    override fun generate(principal: UserPrincipal): Tokens {
        return Tokens(
                generateAccessToken(principal),
                generateRefreshToken(principal),
        )
    }

    private fun generateAccessToken(principal: UserPrincipal): String {
        return Jwts.builder()
                .setHeader(setHeader("ACCESS"))
                .setClaims(setClaims(principal))
                .setSubject(principal.userId.toString())
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + accessExpirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    private fun generateRefreshToken(principal: UserPrincipal): String {
        return Jwts.builder()
                .setHeader(setHeader("REFRESH"))
                .setClaims(setClaims(principal))
                .setSubject(principal.userId.toString())
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + refreshExpirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    private fun setHeader(type: String): Map<String, Any> {
        return mapOf("type" to "JWT", "tokenType" to type, "alg" to "HS256")
    }

    private fun setClaims(principal: UserPrincipal): Map<String, Any> {
        return mapOf("ROLE" to principal.role.role)
    }
}
