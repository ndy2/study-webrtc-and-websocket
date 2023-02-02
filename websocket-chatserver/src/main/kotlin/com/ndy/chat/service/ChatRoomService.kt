package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatRoom

interface ChatRoomService {

    fun createChatRoom(name: String): ChatRoom

    fun findAllRoom(): List<ChatRoom>

    fun findChatRoomById(roomId: String): ChatRoom
}