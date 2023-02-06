package com.ndy.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan(basePackages = ["com.ndy.chat.config"])
@SpringBootApplication
class WebsocketChatserverApplication

fun main(args: Array<String>) {
    runApplication<WebsocketChatserverApplication>(*args)
}
