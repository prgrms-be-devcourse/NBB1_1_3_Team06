package com.nbe3.api.auth

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.nbe3.api.auth.dto.OAuthConnectionResponse
import com.nbe3.api.global.dto.Response
import com.nbe3.api.global.util.TokenUtils
import com.nbe3.domain.auth.OAuthService
import com.nbe3.domain.auth.Tokens

@RestController
@RequestMapping("/api/v1/oauth")
class OAuthApi(private val oAuthService: OAuthService) {

    @GetMapping("/login/connection")
    fun oAuthConnection(): Response<OAuthConnectionResponse> {
        val oAuthConnectionResponse: OAuthConnectionResponse =
            OAuthConnectionResponse.from(oAuthService.connectionUrl)
        return Response.success(oAuthConnectionResponse)
    }

    @GetMapping("/login")
    fun login(@RequestParam code: String): ResponseEntity<Response<Void>> {
        val tokens: Tokens = oAuthService.login(code)
        val header: HttpHeaders = TokenUtils.createTokenHeaders(tokens)
        return ResponseEntity.ok().headers(header).body(Response.success())
    }
}
