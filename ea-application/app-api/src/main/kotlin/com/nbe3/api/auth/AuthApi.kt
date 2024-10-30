package com.nbe3.api.auth

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

import com.nbe3.api.auth.dto.LoginRequest
import com.nbe3.api.auth.dto.SignupRequest
import com.nbe3.api.auth.dto.TokensRequest
import com.nbe3.api.global.dto.Response
import com.nbe3.api.global.util.TokenUtils
import com.nbe3.domain.auth.AuthService
import com.nbe3.domain.auth.Tokens
import com.nbe3.domain.auth.UserPrincipal

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signupRequest: SignupRequest): Response<Void> {
        authService.signUp(signupRequest.toUserProfile())
        return Response.success()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Response<Void>> {
        val tokens: Tokens = authService.login(loginRequest.toLogin())
        val headers: HttpHeaders = TokenUtils.createTokenHeaders(tokens)
        return ResponseEntity.ok().headers(headers).body(Response.success())
    }

    @DeleteMapping("/logout")
    fun logout(@AuthenticationPrincipal userPrincipal: UserPrincipal): Response<Void> {
        authService.logout(userPrincipal.userId)
        return Response.success()
    }

    @PostMapping("/reissue")
    fun reissue(@RequestBody tokenRequestDto: TokensRequest): Response<Tokens> {
        return Response.success(authService.updateToken(tokenRequestDto.to()))
    }
}
