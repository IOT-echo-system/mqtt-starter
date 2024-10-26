package com.robotutor.iot.services

import com.robotutor.iot.models.Message
import com.robotutor.iot.models.MqttTopicName
import com.robotutor.loggingstarter.LogDetails
import com.robotutor.loggingstarter.Logger
import com.robotutor.loggingstarter.serializer.DefaultSerializer
import org.springframework.stereotype.Service

@Service
class MqttPublisher(private val mqttClientService: MqttClientService) {
    val logger = Logger(this::class.java)
    fun publish(topicName: MqttTopicName, message: Message) {
        val mqttClient = mqttClientService.connect()
        val payload = DefaultSerializer.serialize(message).toByteArray()
        mqttClient.publish(topicName.toString(), payload, 1, false)
        logger.info(LogDetails(message = "Successfully published topic $topicName"))
    }
}
