package com.ndy.chat.config.websocket

import com.ndy.chat.config.jwt.JwtTokenDecoder
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component

@Component
class JwtBearerTokenAuthenticationInterceptor(
    private val jwtTokenDecoder: JwtTokenDecoder
) : ChannelInterceptor {

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        StompHeaderAccessor.getFirstNativeHeader("token", message.headers)
            ?.let { jwtTokenDecoder.validate(it) }

        return message
    }
}