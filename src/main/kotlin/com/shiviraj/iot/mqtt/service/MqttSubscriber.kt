package com.shiviraj.iot.mqtt.service

import com.shiviraj.iot.loggingstarter.details.LogDetails
import com.shiviraj.iot.loggingstarter.logger.Logger
import com.shiviraj.iot.loggingstarter.serializer.DefaultSerializer
import com.shiviraj.iot.mqtt.model.Message
import com.shiviraj.iot.mqtt.model.MqttTopicName
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
