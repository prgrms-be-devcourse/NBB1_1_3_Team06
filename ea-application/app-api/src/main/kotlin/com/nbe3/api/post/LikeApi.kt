package com.nbe3.api.post

import com.nbe3.api.global.dto.Response
import com.nbe3.api.post.dto.LikeRequest
import com.nbe3.domain.auth.UserPrincipal
import com.nbe3.domain.posts.LikeInfo
import com.nbe3.domain.posts.LikeService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/likes")
class LikeApi (private val likeService: LikeService){

    @PostMapping
    fun postLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Unit> {
        likeService.addLike(LikeInfo.of(request.postsId, userPrincipal.userId))
        return Response.success()
    }

    @DeleteMapping
    fun deleteLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Unit> {
        likeService.cancelLike(LikeInfo.of(request.postsId, userPrincipal.userId))
        return Response.success()
    }
}
