package com.ndy.chat.domain.repository

import com.ndy.chat.domain.entity.ChatRoom
import org.springframework.data.repository.CrudRepository

interface ChatRoomRepository : CrudRepository<ChatRoom, String> {

    fun findByRoomId(roomId: String): ChatRoom?
}