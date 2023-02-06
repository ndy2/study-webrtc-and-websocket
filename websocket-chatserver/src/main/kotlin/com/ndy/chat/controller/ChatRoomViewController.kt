package com.ndy.chat.controller

import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/chat")
@Controller
class ChatRoomViewController {

    private val log = KotlinLogging.logger {}

    @GetMapping("/room")
    fun chatRoomsView(
        req : HttpServletRequest
    ) : String {
        log.info { "visit chat rooms view, ${req.requestURI}" }
        return "/chat/room"
    }

    @GetMapping("/room/enter/{roomId}")
    fun chatRoomDetailView(model: Model, @PathVariable roomId: String): String {
        log.info { "visit detailed chat room view with roomId :  $roomId" }

        model.addAttribute("roomId", roomId)
        return "/chat/roomdetail"
    }
}
