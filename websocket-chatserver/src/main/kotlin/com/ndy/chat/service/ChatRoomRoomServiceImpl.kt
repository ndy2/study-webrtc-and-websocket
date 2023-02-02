package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatRoom
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatRoomRoomServiceImpl : ChatRoomService {

    private val store = mutableMapOf<String, ChatRoom>()

    override fun createChatRoom(chatRoomName: String): ChatRoom {
        val chatRoomId = getNewChatRoomId()
        return ChatRoom(
            chatRoomId,
            chatRoomName,
        ).also { store[chatRoomId] = it }
    }

    override fun findAllRoom(): List<ChatRoom> {
        return store.values.toList()
    }

    override fun findChatRoomById(chatRoomId: String): ChatRoom {
        return store[chatRoomId] ?: throw IllegalArgumentException("no such chat room Id $chatRoomId")
    }

    private fun getNewChatRoomId() = UUID.randomUUID().toString()
}