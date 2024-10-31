package com.nbe2.api.chatbot

import com.nbe2.api.chatbot.dto.ChatResponse
import com.nbe2.api.chatbot.dto.QuestionRequest
import com.nbe2.api.chatbot.dto.SessionResponse
import com.nbe2.api.global.dto.Response
import com.nbe2.domain.chatbot.ChatbotService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/chatbot")
class ChatbotApi(private val chatbotService: ChatbotService) {

    @PostMapping("/session")
    fun connectChatbot() = Response.success(SessionResponse.of(chatbotService.openChatMemorySession()))

    @PostMapping("/query")
    fun askOfEmergencyManual(@RequestBody request: QuestionRequest) =
        Response.success(ChatResponse.of(chatbotService.getChatResponse(request.toQuestion())))

    @DeleteMapping("/session")
    fun closeChatbot(@RequestParam id: String): Response<Void> {
        chatbotService.closeChatMemorySession(id)
        return Response.success()
    }
}
