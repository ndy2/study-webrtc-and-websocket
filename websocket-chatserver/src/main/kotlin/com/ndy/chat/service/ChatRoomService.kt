package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatRoom

interface ChatRoomService {

    fun createChatRoom(chatRoomName: String): ChatRoom

    fun findAllRoom(): List<ChatRoom>

    fun findChatRoomById(chatRoomId: String): ChatRoom
}