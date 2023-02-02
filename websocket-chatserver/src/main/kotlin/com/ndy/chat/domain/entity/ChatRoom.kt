package com.ndy.chat.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class ChatRoom(
    val name: String,
) {
    @Id
    val roomId = UUID.randomUUID().toString()
}
