package com.ndy.chat.domain.service

import com.ndy.chat.domain.entity.ChatMessage
import org.springframework.web.socket.WebSocketSession

interface ChatMessageService {

    fun sendChatMessage(chatMessage: ChatMessage, session: WebSocketSession)
}