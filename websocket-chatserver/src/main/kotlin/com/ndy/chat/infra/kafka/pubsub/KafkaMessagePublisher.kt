package com.ndy.chat.infra.kafka.pubsub

import com.ndy.chat.domain.entity.ChatMessage
import com.ndy.chat.service.pubsub.MessagePublisher
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaMessagePublisher(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : MessagePublisher {

    override fun publish(topic: String, message: ChatMessage) {
        kafkaTemplate.send(topic, message)
    }
}