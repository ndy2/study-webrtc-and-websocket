package com.ndy.chat.config

import com.ndy.chat.config.jwt.JwtTokenDecoder
import com.ndy.chat.config.websocket.JwtBearerTokenAuthenticationInterceptor
import com.ndy.chat.config.websocket.WebSocketAuthenticationPrincipalArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@EnableWebSocketMessageBroker
@Configuration(proxyBeanMethods = false)
class CustomWebSocketConfigurer(
    private val jwtInterceptor: JwtBearerTokenAuthenticationInterceptor,
    private val jwtTokenDecoder: JwtTokenDecoder
) : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/sub")
        registry.setApplicationDestinationPrefixes("/pub")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws-stomp")
            .setAllowedOriginPatterns("*")
            .withSockJS()
    }
    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(jwtInterceptor)
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(WebSocketAuthenticationPrincipalArgumentResolver(jwtTokenDecoder))
    }
}