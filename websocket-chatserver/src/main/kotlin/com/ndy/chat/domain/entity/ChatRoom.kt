package com.ndy.chat.domain.entity

import org.springframework.web.socket.WebSocketSession

data class ChatRoom(
    val chatRoomId: String,
    val chatRoomName: String,
) {
    private val chatRoomSessions = mutableSetOf<WebSocketSession>()

    fun handleChatMessageType(
        chatRoomSession: WebSocketSession,
        chatMessage: ChatMessage,
    ) {
        if (chatMessage.type == ChatMessageType.ENTER) {
            chatRoomSessions.add(chatRoomSession)
            chatMessage.content = "${chatMessage.senderUsername} 님이 입장하셨습니다."
        }
    }

    fun sendChatMessagesToAllSessions(
        chatMessage: ChatMessage,
        sendChatMessageToSession: (ChatMessage, WebSocketSession) -> Unit,
    ) {
        chatRoomSessions.forEach { sendChatMessageToSession(chatMessage, it) }
    }

}
