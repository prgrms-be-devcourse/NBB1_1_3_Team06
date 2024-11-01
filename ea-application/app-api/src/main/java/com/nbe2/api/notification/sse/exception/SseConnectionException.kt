package com.nbe2.api.notification.sse.exception

import com.nbe2.common.exception.WebException

object SseConnectionException :
        WebException(SseErrorCode.SSE_CONNECTION_FAILED)
