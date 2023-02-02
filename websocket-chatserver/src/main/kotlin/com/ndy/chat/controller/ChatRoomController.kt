package com.ndy.chat.controller

import com.ndy.chat.domain.entity.ChatRoom
import com.ndy.chat.service.ChatRoomService
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RequestMapping("/chat")
@Controller
class ChatRoomController {

    private val log = KotlinLogging.logger {}

    @GetMapping("/room")
    fun chatRoomsView(model: Model) : String {
        log.info { "visit chat rooms view" }
        return "/chat/room"
    }

    @GetMapping("/room/enter/{roomId}")
    fun chatRoomDetailView(model: Model, @PathVariable roomId: String): String {
        log.info { "visit detailed chat room view with roomId :  $roomId" }

        model.addAttribute("roomId", roomId)
        return "/chat/roomdetail"
    }
}

@RequestMapping("/chat")
@RestController
class ChatRoomRestController(
    val chatRoomService: ChatRoomService
) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/room")
    fun createChatRoom(@RequestParam name: String): ChatRoom {
        log.info { "create chat room, name : $name" }

        return chatRoomService.createChatRoom(name)
    }

    @GetMapping("/rooms")
    fun getAllChatRooms(): List<ChatRoom> {
        return chatRoomService.findAllRoom()
    }

    @GetMapping("/room/{roomId}")
    fun getChatRoomById(@PathVariable roomId: String): ChatRoom {
        log.info { "get chat room id : $roomId" }

        return chatRoomService.findChatRoomById(roomId)
    }
}