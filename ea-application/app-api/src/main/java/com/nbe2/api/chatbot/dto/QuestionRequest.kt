package com.nbe2.api.chatbot.dto

import com.nbe2.domain.chatbot.Question

data class QuestionRequest(val query: String, val sessionId: String) {

    fun toQuestion() = Question(query, sessionId)
}
