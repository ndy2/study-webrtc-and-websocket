package com.ndy.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebsocketChatserverApplication

fun main(args: Array<String>) {
    runApplication<WebsocketChatserverApplication>(*args)
}
