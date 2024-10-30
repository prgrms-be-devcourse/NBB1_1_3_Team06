package com.nbe3.api.post

import com.nbe3.api.global.dto.Response
import com.nbe3.api.post.dto.PostRegisterRequest
import com.nbe3.api.post.dto.PostResponse
import com.nbe3.api.post.dto.PostUpdateRequest
import com.nbe3.common.annotation.PageDefault
import com.nbe3.common.dto.Page
import com.nbe3.common.dto.PageResult
import com.nbe3.domain.auth.UserPrincipal
import com.nbe3.domain.posts.*
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/posts")
class PostApi (private val postService: PostService){


    @PostMapping
    fun postPost(
        @RequestBody @Validated request: PostRegisterRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestParam(name = "file", required = false) fileIds: List<Long?>?
    ): Response<Long?> {
        val postId: Long? =
            postService.save(
                userPrincipal.userId,
                PostWriteInfo.create(
                    request.title,
                    request.content,
                    request.city,
                    Optional.ofNullable(fileIds)
                )
            )
        return Response.success(postId)
    }

    @GetMapping
    fun getLocalPostPage(
        @RequestParam("city") city: City?, @PageDefault page: Page?
    ): Response<PageResult<PostListInfo>?> {
        val postPage: PageResult<PostListInfo>? = postService.findListPageByCity(page, city)
        return Response.success(postPage)
    }

    @GetMapping("/my")
    fun getUserPostPage(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PageDefault page: Page?
    ): Response<PageResult<PostListInfo>?> {
        val postPage: PageResult<PostListInfo>? =
            postService.getUserPostPages(page, userPrincipal.userId)
        return Response.success(postPage)
    }

    @GetMapping("/{postsId}")
    fun getPostDetails(@PathVariable("postsId") postsId: Long?): Response<PostResponse> {
        val postDetailsInfo: PostDetailsInfo = postService.findDetails(postsId)
        return Response.success(PostResponse.from(postDetailsInfo))
    }

    @PutMapping("/{postsId}")
    fun putPost(
        @PathVariable("postsId") postsId: Long?,
        @RequestBody @Validated request: PostUpdateRequest,
        @RequestParam(name = "file", required = false) fileIds: List<Long?>?
    ): Response<Long?> {
        val postId: Long? =
            postService.update(
                postsId,
                PostWriteInfo.create(
                    request.title,
                    request.content,
                    request.city,
                    Optional.ofNullable(fileIds)
                )
            )
        return Response.success(postId)
    }

    @DeleteMapping("/{postsId}")
    fun deletePost(@PathVariable("postsId") postsId: Long?): Response<Unit> {
        postService.delete(postsId)
        return Response.success()
    }
}
