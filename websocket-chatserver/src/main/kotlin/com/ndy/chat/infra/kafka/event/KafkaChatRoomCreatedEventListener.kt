package com.ndy.chat.infra.kafka.event

import com.ndy.chat.infra.kafka.pubsub.KafkaMessageSubscriber
import com.ndy.chat.service.event.ChatRoomCreatedEvent
import com.ndy.chat.service.event.ChatRoomCreatedEventListener
import mu.KotlinLogging
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.event.EventListener
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.listener.MessageListenerContainer
import org.springframework.stereotype.Component

@Component
class KafkaChatRoomCreatedEventListener(
    val kafkaAdmin: KafkaAdmin,
    val kafkaListenerContainerFactory: KafkaListenerContainerFactory<MessageListenerContainer>,
    val kafkaMessageSubscriber: KafkaMessageSubscriber,
) : ChatRoomCreatedEventListener {

    private val log = KotlinLogging.logger {}

    @EventListener
    override fun onCreated(event: ChatRoomCreatedEvent) {
        log.info { "event :  $event" }

        val newTopicName = "chatRoom.${event.roomId}"
        val newChatRoomTopic = NewTopic(newTopicName, 1, 1)
        kafkaAdmin.createOrModifyTopics(newChatRoomTopic)

        val messageListenerContainer = kafkaListenerContainerFactory.createContainer(newTopicName)
        messageListenerContainer.setupMessageListener(kafkaMessageSubscriber)
        messageListenerContainer.start()
    }
}