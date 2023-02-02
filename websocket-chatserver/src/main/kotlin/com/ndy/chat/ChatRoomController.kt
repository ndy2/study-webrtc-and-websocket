package com.ndy.chat

import com.ndy.chat.domain.entity.ChatRoom
import com.ndy.chat.service.ChatRoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/chatroom")
@RestController
class ChatRoomController(
    val chatRoomService: ChatRoomService
) {

    @PostMapping
    fun create(
        @RequestParam chatRoomName: String
    ): ChatRoom {
        return chatRoomService.createChatRoom(chatRoomName)
    }

    @GetMapping
    fun getAllChatRoom(): List<ChatRoom> {
        return chatRoomService.findAllRoom()
    }

}