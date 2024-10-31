package com.nbe2.api.auth

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

import com.nbe2.api.auth.dto.LoginRequest
import com.nbe2.api.auth.dto.SignupRequest
import com.nbe2.api.auth.dto.TokensRequest
import com.nbe2.api.global.dto.Response
import com.nbe2.api.global.util.TokenUtils
import com.nbe2.domain.auth.AuthService
import com.nbe2.domain.auth.Tokens
import com.nbe2.domain.auth.UserPrincipal

@RestController
@RequestMapping("/api/v1/auth")
class AuthApi(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signupRequest: SignupRequest): Response<Unit> {
        authService.signUp(signupRequest.toUserProfile())
        return Response.success()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Response<Unit>> {
        val tokens = authService.login(loginRequest.toLogin())
        val headers = TokenUtils.createTokenHeaders(tokens)
        return ResponseEntity.ok().headers(headers).body(Response.success())
    }

    @DeleteMapping("/logout")
    fun logout(@AuthenticationPrincipal userPrincipal: UserPrincipal): Response<Unit> {
        authService.logout(userPrincipal.userId)
        return Response.success()
    }

    @PostMapping("/reissue")
    fun reissue(@RequestBody tokenRequestDto: TokensRequest): Response<Tokens> =
        Response.success(authService.updateToken(tokenRequestDto.to()))
}
