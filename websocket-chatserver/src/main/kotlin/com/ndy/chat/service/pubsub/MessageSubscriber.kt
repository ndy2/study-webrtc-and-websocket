package com.ndy.chat.service.pubsub

import com.ndy.chat.domain.entity.ChatMessage
import org.springframework.messaging.Message

interface MessageSubscriber {

    fun onMessage(message: Message<ChatMessage>)
}