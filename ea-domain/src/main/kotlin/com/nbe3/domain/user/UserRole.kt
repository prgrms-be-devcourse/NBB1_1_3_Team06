package com.nbe3.domain.user

import java.util.*

enum class UserRole(val role: String) {
    USER("ROLE_USER"),
    MEDICAL_PERSON("ROLE_MEDICAL_PERSON"),
    ADMIN("ROLE_ADMIN");

    companion object {
        fun findByRole(role: String): UserRole {
            return Arrays.stream(entries.toTypedArray())
                .filter { userRole: UserRole -> userRole.role == role }
                .findFirst()
                .orElse(null)
        }
    }
}
