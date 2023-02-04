package com.ndy.chat.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.ndy.chat.domain.entity.ChatMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import java.util.function.Supplier

@EnableKafka
@Configuration(proxyBeanMethods = false)
class KafkaConfig(
    private val mapper: ObjectMapper
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<*, *> {
        val properties = mutableMapOf<String, Any>()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        properties[ConsumerConfig.GROUP_ID_CONFIG] = "chatRooms"

        return DefaultKafkaConsumerFactory<String, ChatMessage>(
            properties,
            Supplier { StringDeserializer() },
            Supplier { Deserializer<ChatMessage> { _, data -> mapper.readValue(data, ChatMessage::class.java) } },
            false
        )
    }

    @Bean
    fun producerFactory(): ProducerFactory<*, *> {
        val properties = mutableMapOf<String, Any>()
        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"

        return DefaultKafkaProducerFactory<String, ChatMessage>(
            properties,
            Supplier { StringSerializer() },
            Supplier { Serializer<ChatMessage> { _, data -> mapper.writeValueAsBytes(data) } },
            false
        )
    }

}