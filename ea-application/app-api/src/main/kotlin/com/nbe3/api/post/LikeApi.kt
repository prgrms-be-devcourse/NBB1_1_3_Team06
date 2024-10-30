package com.nbe3.api.post

import com.nbe2.api.global.dto.Response
import com.nbe3.api.post.dto.LikeRequest
import com.nbe3.domain.posts.LikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
class LikeApi {
    private val likeService: LikeService? = null

    @PostMapping
    fun postLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Void> {
        likeService.addLike(LikeInfo.of(request.postsId(), userPrincipal.userId()))
        return Response.success()
    }

    @DeleteMapping
    fun deleteLike(
        @RequestBody request: LikeRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): Response<Void> {
        likeService.cancelLike(LikeInfo.of(request.postsId(), userPrincipal.userId()))
        return Response.success()
    }
}
