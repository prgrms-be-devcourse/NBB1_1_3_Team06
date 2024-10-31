package com.nbe2.infra.chatbot.client

import com.nbe2.domain.chatbot.ChatbotService
import com.nbe2.domain.chatbot.Question
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.stereotype.Component

@Component
class OpenAiClient(
    private val chatClient: ChatClient,
    private val chatMemory: ChatMemory
) : ChatbotService {

    override fun getChatResponse(question: Question): String =
        chatClient
            .prompt()
            .user(question.query)
            .advisors { advisorSpec ->
                advisorSpec
                    .param(
                        AbstractChatMemoryAdvisor
                            .CHAT_MEMORY_CONVERSATION_ID_KEY,
                        question.sessionId
                    )
                    .param(
                        AbstractChatMemoryAdvisor
                            .CHAT_MEMORY_RETRIEVE_SIZE_KEY,
                        100
                    )
            }
            .call()
            .content()

    override fun closeChatMemorySession(sessionId: String) {
        chatMemory.clear(sessionId)
    }
}
