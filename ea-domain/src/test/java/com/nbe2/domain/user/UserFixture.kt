package com.nbe2.domain.user

import com.nbe2.domain.global.EMAIL
import com.nbe2.domain.global.ID
import com.nbe2.domain.global.NAME
import com.nbe2.domain.global.PASSWORD

fun createUser(): User {
    return User.of(NAME, EMAIL, PASSWORD)
}

fun createPendingUser(): User {
    return User.of(NAME, EMAIL, PASSWORD)
}

fun createUserWithId(): User {
    val user = User.of(NAME, EMAIL, PASSWORD)

    try {
        val field = User::class.java.getDeclaredField("id")
        field.isAccessible = true
        field[user] = ID
    } catch (ignored: java.lang.Exception) {
    }

    return user
}

fun createUserProfile(): UserProfile {
    return UserProfile(NAME, EMAIL, PASSWORD)
}

fun createMedicalProfile(): MedicalProfile {
    return MedicalProfile(ID, ID)
}

fun createUserProfileWithLicense(): UserProfileWithLicense {
    return UserProfileWithLicense(ID, NAME, EMAIL, ID)
}
