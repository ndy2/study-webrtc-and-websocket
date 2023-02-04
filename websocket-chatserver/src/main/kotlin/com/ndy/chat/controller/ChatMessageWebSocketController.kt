package com.ndy.chat.controller

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.domain.entity.ChatMessageType
import com.ndy.chat.service.ChatMessageService
import com.ndy.chat.service.pubsub.MessagePublisher
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping
@Controller
class ChatMessageWebSocketController(
    private val chatMessageService: ChatMessageService
) {
    @MessageMapping("/chat/message")
    fun create(
        chatMessage: ChatMessage
    ) {
        chatMessageService.sendChatMessage(chatMessage)
    }
}