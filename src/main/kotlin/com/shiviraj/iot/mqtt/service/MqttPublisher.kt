package com.shiviraj.iot.mqtt.service

import com.shiviraj.iot.loggingstarter.details.LogDetails
import com.shiviraj.iot.loggingstarter.logger.Logger
import com.shiviraj.iot.loggingstarter.serializer.DefaultSerializer
import com.shiviraj.iot.mqtt.model.Message
import com.shiviraj.iot.mqtt.model.MqttTopicName
import org.springframework.stereotype.Service

@Service
class MqttPublisher(private val mqttClientService: MqttClientService) {
    val logger = Logger(this::class.java)
    fun publish(topicName: MqttTopicName, message: Message) {
        val mqttClient = mqttClientService.connect()
        val payload = DefaultSerializer.serialize(message).toByteArray()
        mqttClient.publish(topicName.toString(), payload, 1, false)
        logger.info(LogDetails(message = "Successfully subscribed topic with $topicName"))
    }
}
