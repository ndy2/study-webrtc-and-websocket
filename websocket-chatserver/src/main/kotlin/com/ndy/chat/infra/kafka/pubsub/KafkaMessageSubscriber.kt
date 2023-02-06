package com.ndy.chat.infra.kafka.pubsub

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.service.pubsub.MessageSubscriber
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.MessageListener
import org.springframework.messaging.Message
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Component

@Component
class KafkaMessageSubscriber(
    private val messagingTemplate: SimpMessagingTemplate,
) : MessageSubscriber, MessageListener<String, ChatMessage> {

    private val log = KotlinLogging.logger {}

    @KafkaListener(id = "kafka-onChatMessage", topicPattern = "chatRoom\\..*", groupId = "chatRooms")
    override fun onMessage(message: Message<ChatMessage>) {
        val chatMessage = message.payload

        log.info { "kafka consumed message : $chatMessage" }

        messagingTemplate.convertAndSend("/sub/chat/room/${chatMessage.roomId}", chatMessage)
    }

    override fun onMessage(data: ConsumerRecord<String, ChatMessage>) {
        this.onMessage(convert(data))
    }

    private fun convert(data: ConsumerRecord<String, ChatMessage>) = GenericMessage(
        data.value(),
        data.headers().associateBy(
            { header -> header.key() },
            { header -> header.value() }
        ))

}
