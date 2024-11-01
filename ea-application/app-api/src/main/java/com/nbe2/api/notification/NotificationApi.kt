package com.nbe2.api.notification

import com.nbe2.api.global.dto.Response
import com.nbe2.api.notification.dto.NotificationDetailResponse
import com.nbe2.api.notification.dto.UnreadResponse
import com.nbe2.api.notification.sse.SseConnector
import com.nbe2.common.annotation.CursorDefault
import com.nbe2.common.dto.Cursor
import com.nbe2.common.dto.CursorResult
import com.nbe2.domain.auth.UserPrincipal
import com.nbe2.domain.notification.NotificationDetail
import com.nbe2.domain.notification.NotificationService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/api/v1/notifications")
class NotificationApi(
        private val notificationService: NotificationService,
        private val sseConnector: SseConnector,
) {

    @GetMapping(
            value = ["/subscribe"],
            produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
    )
    fun subscribe(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<SseEmitter> {
        val emitter: SseEmitter = sseConnector.connect(userPrincipal.userId)
        return ResponseEntity.ok(emitter)
    }

    @GetMapping("/my")
    fun getNotificationHistory(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @CursorDefault cursor: Cursor,
    ): Response<CursorResult<NotificationDetailResponse>> {
        val history: CursorResult<NotificationDetail> =
                notificationService.getNotificationHistory(
                        userPrincipal.userId,
                        cursor,
                )
        return Response.success(history.map(NotificationDetailResponse::from))
    }

    @GetMapping("/my/unread")
    fun getUnreadNotificationExistence(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ) =
            Response.success(
                    UnreadResponse.of(
                            notificationService.hasUnreadNotification(
                                    userPrincipal.userId
                            )
                    )
            )
}
