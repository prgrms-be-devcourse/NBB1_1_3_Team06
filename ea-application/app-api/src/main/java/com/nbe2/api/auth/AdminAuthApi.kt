package com.nbe2.api.auth

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

import com.nbe2.api.auth.dto.PendingUserResponse
import com.nbe2.api.global.dto.Response
import com.nbe2.common.annotation.PageDefault
import com.nbe2.common.dto.Page
import com.nbe2.common.dto.PageResult
import com.nbe2.domain.auth.AdminAuthService
import com.nbe2.domain.auth.UserPrincipal

@RestController
@RequestMapping("/api/v1/auth/admin")
class AdminAuthApi(private val adminAuthService: AdminAuthService) {

    @GetMapping("/pendings")
    fun searchPendingUsers(@PageDefault page: Page): Response<PageResult<PendingUserResponse>> {
        val userPageResult = adminAuthService.searchPendingUsers(page)
        return Response.success(userPageResult.map(PendingUserResponse::from))
    }

    @PatchMapping("/pendings")
    fun approveSignupRequest(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Void> {
        adminAuthService.approveSignup(userPrincipal.userId)
        return Response.success()
    }
}
