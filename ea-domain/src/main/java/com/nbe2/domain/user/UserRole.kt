package com.nbe2.domain.user

import java.util.*

enum class UserRole(val role: String) {
    USER("ROLE_USER"),
    MEDICAL_PERSON("ROLE_MEDICAL_PERSON"),
    ADMIN("ROLE_ADMIN");

    companion object {
        @JvmStatic
        fun findByRole(role: String) =
            Arrays.stream(entries.toTypedArray())
                .filter { userRole: UserRole -> userRole.role == role }
                .findFirst()
                .orElse(null)
    }
}
