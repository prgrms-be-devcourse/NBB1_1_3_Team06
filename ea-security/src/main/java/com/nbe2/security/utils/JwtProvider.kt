package com.nbe2.security.utils

import com.nbe2.domain.auth.TokenProvider
import com.nbe2.domain.auth.UserPrincipal
import com.nbe2.domain.user.UserRole
import com.nbe2.security.exception.JwtExpriedException
import com.nbe2.security.exception.JwtUnsupportedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import java.security.Key
import javax.crypto.spec.SecretKeySpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtProvider(@Value("\${jwt.secret-key}") private val secretKey: String) :
        TokenProvider {

    override fun getTokenUserPrincipal(token: String): UserPrincipal {
        val roleName = getUserRole(token)
        val userId = getUserId(token).toLong()
        return UserPrincipal.of(userId, UserRole.findByRole(roleName))
    }

    private fun getUserId(token: String): String {
        return getTokenSubject(secretKey.toKey(), token)
    }

    private fun getUserRole(token: String): String {
        return getTokenClaims(secretKey.toKey(), token)["ROLE"].toString()
    }

    private fun String.toKey(): Key {
        return SecretKeySpec(
                this.toByteArray(),
                SignatureAlgorithm.HS256.jcaName,
        )
    }

    private fun getTokenClaims(key: Key, token: String): Claims {
        return try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body
        } catch (e: ExpiredJwtException) {
            throw JwtExpriedException.EXCEPTION
        } catch (e: UnsupportedJwtException) {
            throw JwtUnsupportedException.EXCEPTION
        }
    }

    private fun getTokenSubject(key: Key, token: String): String {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
    }
}
