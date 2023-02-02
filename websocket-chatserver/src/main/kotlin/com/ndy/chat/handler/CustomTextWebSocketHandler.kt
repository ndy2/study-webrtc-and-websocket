package com.ndy.chat.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.domain.service.ChatMessageService
import com.ndy.chat.service.ChatRoomService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class CustomTextWebSocketHandler(
    val chatRoomService: ChatRoomService,
    val chatMessageService: ChatMessageService,
    val mapper: ObjectMapper
) : TextWebSocketHandler() {

    private val log = KotlinLogging.logger {}

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        log.info { "payload : $payload" }

        val chatRoomSession = session
        val chatMessage = mapper.readValue<ChatMessage>(payload)
        val chatRoom = chatRoomService.findChatRoomById(chatMessage.roomId)

        chatRoom.handleChatMessageType(chatRoomSession, chatMessage)
        chatRoom.sendChatMessagesToAllSessions(chatMessage, ::sendChatMessageToSession)
    }

    private fun sendChatMessageToSession(chatMessage: ChatMessage, webSocketSession: WebSocketSession) {
        chatMessageService.sendChatMessage(chatMessage, webSocketSession)
    }
}