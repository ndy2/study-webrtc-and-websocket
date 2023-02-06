package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.domain.entity.ChatMessageType
import com.ndy.chat.service.pubsub.MessagePublisher
import org.springframework.stereotype.Service

@Service
class ChatMessageServiceImpl(
    private val messagePublisher: MessagePublisher
) : ChatMessageService {

    override fun sendChatMessage(chatMessage: ChatMessage, username: String) {
        if (chatMessage.type == ChatMessageType.ENTER)
            chatMessage.message = "$username 님이 입장 하셨습니다."

        messagePublisher.publish("chatRoom.${chatMessage.roomId}", chatMessage)
    }
}