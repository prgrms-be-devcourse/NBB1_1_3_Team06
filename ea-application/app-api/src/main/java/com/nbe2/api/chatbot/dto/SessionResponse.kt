package com.nbe2.api.chatbot.dto

data class SessionResponse(val sessionId: String) {

    companion object {
        fun of(sessionId: String) = SessionResponse(sessionId)
    }
}
