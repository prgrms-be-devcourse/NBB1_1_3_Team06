package com.nbe2.api.notification.dto

data class UnreadResponse(val hasUnread: Boolean) {
    companion object {
        fun of(hasUnread: Boolean) = UnreadResponse(hasUnread)
    }
}
