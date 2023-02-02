package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatRoom
import com.ndy.chat.domain.repository.ChatRoomRepository
import com.ndy.chat.util.notFound
import org.springframework.stereotype.Service

@Service
class ChatRoomRoomServiceImpl(
    val chatRoomRepository: ChatRoomRepository
) : ChatRoomService {

    override fun createChatRoom(name: String): ChatRoom {
        return ChatRoom(name).also { chatRoomRepository.save(it) }
    }

    override fun findAllRoom(): List<ChatRoom> {
        return chatRoomRepository.findAll().toList()
    }

    override fun findChatRoomById(roomId: String): ChatRoom {
        return chatRoomRepository.findByRoomId(roomId) ?: notFound(ChatRoom::class, roomId)
    }
}