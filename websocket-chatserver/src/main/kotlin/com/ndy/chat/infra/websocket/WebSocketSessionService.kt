package com.ndy.chat.infra.websocket

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.domain.service.ChatMessageService
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class WebSocketSessionService : ChatMessageService {

    override fun sendChatMessage(chatMessage: ChatMessage, session: WebSocketSession) {
        session.sendMessage(TextMessage(chatMessage.content))
    }
}