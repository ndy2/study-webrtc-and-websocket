package com.ndy.chat.service

import com.ndy.chat.domain.entity.ChatRoom
import com.ndy.chat.domain.repository.ChatRoomRepository
import com.ndy.chat.service.event.ChatRoomCreatedEvent
import com.ndy.chat.util.notFound
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ChatRoomRoomServiceImpl(
    val chatRoomRepository: ChatRoomRepository,
    val eventPublisher: ApplicationEventPublisher
) : ChatRoomService {

    override fun createChatRoom(name: String): ChatRoom {
        return ChatRoom(name).also {
            chatRoomRepository.save(it)
            eventPublisher.publishEvent(ChatRoomCreatedEvent(it.roomId, it.name))
        }
    }

    override fun findAllRoom(): List<ChatRoom> {
        return chatRoomRepository.findAll().toList()
    }

    override fun findChatRoomById(roomId: String): ChatRoom {
        return chatRoomRepository.findByRoomId(roomId) ?: notFound(ChatRoom::class, roomId)
    }
}