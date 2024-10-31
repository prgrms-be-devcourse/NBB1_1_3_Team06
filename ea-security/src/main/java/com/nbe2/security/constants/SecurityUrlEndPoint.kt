package com.nbe3.security.constants

import com.nbe2.domain.user.UserRole
import org.springframework.http.HttpMethod

enum class SecurityUrlEndPoint(
    val method: HttpMethod,
    val url: String,
    val userRole: UserRole? = null
) {
    // All
    GUEST_OAUTH_POST(HttpMethod.POST, "/api/v1/oauth/**"),
    GUEST_AUTH_POST(HttpMethod.POST, "/api/v1/auth/**"),
    GUEST_NOTICES_GET(HttpMethod.GET, "/api/v1/notices/**"),
    GUEST_REVIEWS_GET(HttpMethod.GET, "/api/v1/reviews/**"),
    GUEST_EMERGENCY_DIRECTIONS_GET(HttpMethod.GET, "/api/v1/directions"),
    GUEST_EMERGENCY_ROOMS_GET(HttpMethod.GET, "/api/v1/emergency-rooms/**"),
    GUEST_POST_GET(HttpMethod.GET, "/api/v1/posts/**"),
    CHAT_POST(HttpMethod.POST, "/api/v1/chatbot/**"),
    CHAT_DELETE(HttpMethod.DELETE, "/api/v1/chatbot/**"),

    // Medical Person
    MEDICAL_PERSON_NOTICES_PUT(HttpMethod.PUT, "/api/v1/notices", UserRole.MEDICAL_PERSON),
    MEDICAL_PERSON_NOTICES_POST(HttpMethod.POST, "/api/v1/notices", UserRole.MEDICAL_PERSON),
    MEDICAL_PERSON_NOTICES_DELETE(HttpMethod.POST, "/api/v1/notices", UserRole.MEDICAL_PERSON),

    // ADMIN
    ADMIN_PENDINGS_ALL(HttpMethod.POST, "/api/v1/auth/admin/pendings", UserRole.ADMIN),
    ADMIN_PENDINGS_GET(HttpMethod.GET, "/api/v1/auth/admin/pendings", UserRole.ADMIN)
}
