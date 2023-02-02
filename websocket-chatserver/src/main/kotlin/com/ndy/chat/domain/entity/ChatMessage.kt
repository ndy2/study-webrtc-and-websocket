package com.ndy.chat.domain.entity

data class ChatMessage(
    val type: ChatMessageType,
    val roomId: String,
    val senderUsername: String,
    var content: String,
)
