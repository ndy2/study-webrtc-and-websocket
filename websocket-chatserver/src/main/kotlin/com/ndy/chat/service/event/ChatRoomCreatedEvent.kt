package com.ndy.chat.service.event

data class ChatRoomCreatedEvent(
    val roomId: String,
    val roomName: String,
)