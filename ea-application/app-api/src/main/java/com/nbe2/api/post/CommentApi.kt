package com.nbe2.api.post

import com.nbe2.api.global.dto.Response
import com.nbe2.api.post.dto.CommentRegisterRequest
import com.nbe2.api.post.dto.CommentResponse
import com.nbe2.api.post.dto.CommentUpdateRequest
import com.nbe2.domain.auth.UserPrincipal
import com.nbe2.domain.posts.CommentInfo
import com.nbe2.domain.posts.CommentReadInfo
import com.nbe2.domain.posts.CommentService
import com.nbe2.domain.posts.CommentWriteInfo
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/comments")
class CommentApi(private val commentService: CommentService) {

    @PostMapping
    fun postComment(
            @RequestBody @Validated request: CommentRegisterRequest,
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): Response<Long?> {
        val postId: Long? =
                commentService.save(
                        request.postsId,
                        CommentWriteInfo.create(
                                userPrincipal.userId,
                                CommentInfo.of(request.content),
                        ),
                )
        return Response.success(postId)
    }

    @GetMapping
    fun getPostComments(
            @RequestParam("postsId") postId: Long?
    ): Response<List<CommentResponse>> {
        val commentReadInfos: List<CommentReadInfo> =
                commentService.getPostComments(postId)
        val commentResponses: List<CommentResponse> =
                commentReadInfos.map(CommentResponse::from).toList()
        return Response.success(commentResponses)
    }

    @PutMapping("/{commentsId}")
    fun putComment(
            @PathVariable("commentsId") commentsId: Long?,
            @RequestBody request: CommentUpdateRequest,
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): Response<Long?> {
        val postId: Long? =
                commentService.update(
                        commentsId!!,
                        CommentWriteInfo.create(
                                userPrincipal.userId,
                                CommentInfo.of(request.content),
                        ),
                )
        return Response.success(postId)
    }

    @DeleteMapping("{commentsId}")
    fun deleteComment(
            @PathVariable("commentsId") commentsId: Long?
    ): Response<Void> {
        commentService.delete(commentsId!!)
        return Response.success()
    }
}
