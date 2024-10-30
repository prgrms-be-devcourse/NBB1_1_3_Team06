package com.nbe3.api.user.dto

import com.nbe3.domain.user.UpdatePassword

data class UpdatePasswordRequest(val previous: String, val toChange: String) {

    fun toPassword(): UpdatePassword {
        return UpdatePassword(previous, toChange)
    }
}
