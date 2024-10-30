package com.nbe3.domain.user

data class MyProfile(val name: String, val email: String, val hasMedicalAuthority: Boolean) {

    companion object {
        fun from(user: User): MyProfile {
            return MyProfile(
                user.name, user.email, user.role == UserRole.MEDICAL_PERSON
            )
        }
    }
}
