package com.nbe2.api.user.dto

import com.nbe2.domain.user.UpdatePassword

data class UpdatePasswordRequest(val previous: String, val toChange: String) {

    fun toPassword() = UpdatePassword(previous, toChange)
}
