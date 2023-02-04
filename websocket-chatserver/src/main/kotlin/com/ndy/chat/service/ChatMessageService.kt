package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatMessage

interface ChatMessageService {

    fun sendChatMessage(chatMessage: ChatMessage)
}