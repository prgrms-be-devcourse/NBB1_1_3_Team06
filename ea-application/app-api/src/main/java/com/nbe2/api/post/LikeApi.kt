package com.nbe2.api.post

import com.nbe2.api.global.dto.Response
import com.nbe2.api.post.dto.LikeRequest
import com.nbe2.domain.auth.UserPrincipal
import com.nbe2.domain.posts.LikeInfo
import com.nbe2.domain.posts.LikeService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/likes")
class LikeApi (private val likeService: LikeService){

    @PostMapping
    fun postLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Void> {
        likeService.addLike(LikeInfo.of(request.postsId, userPrincipal.userId))
        return Response.success()
    }

    @DeleteMapping
    fun deleteLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Void> {
        likeService.cancelLike(LikeInfo.of(request.postsId, userPrincipal.userId))
        return Response.success()
    }
}
