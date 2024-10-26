package com.robotutor.iot.services

import com.robotutor.iot.models.Message
import com.robotutor.iot.models.MqttTopicName
import com.robotutor.loggingstarter.LogDetails
import com.robotutor.loggingstarter.Logger
import com.robotutor.loggingstarter.serializer.DefaultSerializer
import org.springframework.stereotype.Service

@Service
class MqttSubscriber(private val mqttClientService: MqttClientService) {
    val logger = Logger(this::class.java)
    fun <T : Message> subscribe(topicName: MqttTopicName, messageType: Class<T>, handler: (T) -> Unit) {
        val mqttClient = mqttClientService.connect()
        mqttClient.subscribe(topicName.toString(), 1) { _, msg ->
            try {
                handler(DefaultSerializer.deserialize(msg.toString(), messageType))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        logger.info(LogDetails(message = "Successfully subscribed topic with $topicName"))
    }
}
