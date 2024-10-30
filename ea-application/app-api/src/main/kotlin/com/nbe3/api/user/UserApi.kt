package com.nbe3.api.user

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

import com.nbe3.api.global.dto.Response
import com.nbe3.api.user.dto.MedicalRequest
import com.nbe3.api.user.dto.ProfileResponse
import com.nbe3.api.user.dto.UpdatePasswordRequest
import com.nbe3.api.user.dto.UpdateProfileRequest
import com.nbe3.domain.auth.UserPrincipal
import com.nbe3.domain.user.UserService

@RestController
@RequestMapping("/api/v1/my")
class UserApi(private val userService: UserService) {

    @PatchMapping("/medical")
    fun requestMedicalAuthority(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody medicalRequest: MedicalRequest
    ): Response<Unit> {
        userService.requestMedicalAuthority(
            userPrincipal.userId, medicalRequest.toMedicalProfile()
        )
        return Response.success()
    }

    @GetMapping
    fun getMyProfile(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<ProfileResponse> {
        return Response.success(
            ProfileResponse.from(userService.getMyProfile(userPrincipal.userId))
        )
    }

    @PatchMapping
    fun updateMyProfile(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: UpdateProfileRequest
    ): Response<Unit> {
        userService.updateProfile(userPrincipal.userId, request.toProfile())
        return Response.success()
    }

    @PatchMapping("/password")
    fun changePassword(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: UpdatePasswordRequest
    ): Response<Unit> {
        userService.changePassword(userPrincipal.userId, request.toPassword())
        return Response.success()
    }
}
