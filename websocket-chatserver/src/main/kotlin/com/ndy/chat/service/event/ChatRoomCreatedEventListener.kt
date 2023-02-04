package com.ndy.chat.service.event

interface ChatRoomCreatedEventListener {

    fun onCreated(event: ChatRoomCreatedEvent)
}
