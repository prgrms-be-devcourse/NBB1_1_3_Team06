package com.nbe2.api.user

import com.nbe2.api.global.dto.Response
import com.nbe2.api.user.dto.MedicalRequest
import com.nbe2.api.user.dto.ProfileResponse
import com.nbe2.api.user.dto.UpdatePasswordRequest
import com.nbe2.api.user.dto.UpdateProfileRequest
import com.nbe2.domain.auth.UserPrincipal
import com.nbe2.domain.user.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/my")
class UserApi(private val userService: UserService) {

    @PatchMapping("/medical")
    fun requestMedicalAuthority(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody medicalRequest: MedicalRequest,
    ): Response<Void> {
        userService.requestMedicalAuthority(
                userPrincipal.userId,
                medicalRequest.toMedicalProfile(),
        )
        return Response.success()
    }

    @GetMapping
    fun getMyProfile(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<ProfileResponse> =
            Response.success(
                    ProfileResponse.from(
                            userService.getMyProfile(userPrincipal.userId)
                    )
            )

    @PatchMapping
    fun updateMyProfile(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody request: UpdateProfileRequest,
    ): Response<Void> {
        userService.updateProfile(userPrincipal.userId, request.toProfile())
        return Response.success()
    }

    @PatchMapping("/password")
    fun changePassword(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody request: UpdatePasswordRequest,
    ): Response<Void> {
        userService.changePassword(userPrincipal.userId, request.toPassword())
        return Response.success()
    }
}
