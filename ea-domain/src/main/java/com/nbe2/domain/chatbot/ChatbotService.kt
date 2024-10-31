package com.nbe2.domain.chatbot

import java.util.*

interface ChatbotService {
    fun openChatMemorySession(): String = UUID.randomUUID().toString()

    fun getChatResponse(question: Question): String

    fun closeChatMemorySession(sessionId: String)
}
