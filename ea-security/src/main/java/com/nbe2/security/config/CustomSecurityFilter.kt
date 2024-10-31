package com.nbe3.security.config

import com.nbe2.common.constants.EAConstants
import com.nbe2.domain.auth.UserPrincipal
import com.nbe3.security.exception.JwtNotFountException
import com.nbe3.security.utils.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class CustomSecurityFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwtToken = reversToken(request)
            println("token :: $jwtToken")
            // AccessToken(JWT) 유효한지 검사
            // 유효하지 않으면 Refresh Token을 이용해 새 AccessToken 발급
            val tokenUserPrincipal: UserPrincipal = jwtProvider.getTokenUserPrincipal(jwtToken)
            val grantedAuthorities: List<GrantedAuthority> =
                convertorGrantedAuthority(tokenUserPrincipal.role().role)
            setSecurityContextHolder(tokenUserPrincipal, grantedAuthorities)
        } catch (e: Exception) {
            request.setAttribute("exception", e)
        }
        println("다음으로 이동")
        filterChain.doFilter(request, response)
    }

    private fun setSecurityContextHolder(
        userPrincipal: UserPrincipal, grantedAuthorities: List<GrantedAuthority>
    ) {
        val authenticationToken = UsernamePasswordAuthenticationToken(userPrincipal, null, grantedAuthorities)
        SecurityContextHolder.getContext().setAuthentication(authenticationToken)
    }

    // 사용자의 Role을 Spring Context ROLE 정보에 맞게 변환
    private fun convertorGrantedAuthority(role: String): List<GrantedAuthority> {
        return java.util.List.of<GrantedAuthority>(SimpleGrantedAuthority(role))
    }

    // 헤더에서 토큰 추출
    private fun reversToken(httpServletRequest: HttpServletRequest): String {
        val bearerToken: String = httpServletRequest.getHeader(EAConstants.AUTHORIZATION_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(EAConstants.BEARER)) {
            if (bearerToken.length > EAConstants.BEARER.length) {
                return bearerToken.substring(EAConstants.BEARER.length)
            }
        }
        // 토큰 정보 칸이 비어있으면 없는 토큰으로 간주하고 오류 발생
        throw JwtNotFountException.EXCEPTION
    }


}
