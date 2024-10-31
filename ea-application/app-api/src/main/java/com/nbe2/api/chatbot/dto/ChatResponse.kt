package com.nbe2.api.chatbot.dto

data class ChatResponse(val answer: String) {

    companion object {
        fun of(answer: String) = ChatResponse(answer)
    }
}
