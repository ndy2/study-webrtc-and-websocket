package com.ndy.chat.service.pubsub

import com.ndy.chat.domain.entity.ChatMessage

interface MessagePublisher {

    fun publish(topic: String, message: ChatMessage)
}