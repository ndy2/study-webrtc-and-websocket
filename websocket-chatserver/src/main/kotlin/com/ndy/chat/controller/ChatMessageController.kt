package com.ndy.chat.controller

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.domain.entity.ChatMessageType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping
@Controller
class ChatMessageController(
    val messagingTemplate: SimpMessageSendingOperations
) {
    @MessageMapping("/chat/message")
    fun create(
        chatMessage: ChatMessage
    ) {
        if (chatMessage.type == ChatMessageType.ENTER)
            chatMessage.message = "${chatMessage.sender}님이 입장 하셨습니다."

        messagingTemplate.convertAndSend("/sub/chat/room/${chatMessage.roomId}", chatMessage);
    }
}