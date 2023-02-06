package com.ndy.chat.controller

import com.ndy.chat.config.jwt.username
import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.service.ChatMessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Controller

@Controller
class ChatMessageWebSocketController(
    private val chatMessageService: ChatMessageService
) {
    @MessageMapping("/chat/message")
    fun create(
        chatMessage: ChatMessage,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        println("message ${chatMessage} by user : ${jwt.username()}")
        chatMessageService.sendChatMessage(chatMessage, jwt.username())
    }
}