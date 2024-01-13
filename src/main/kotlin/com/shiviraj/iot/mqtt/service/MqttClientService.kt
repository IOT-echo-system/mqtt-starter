package com.shiviraj.iot.mqtt.service

import com.shiviraj.iot.loggingstarter.details.LogDetails
import com.shiviraj.iot.loggingstarter.logger.Logger
import com.shiviraj.iot.mqtt.config.MqttConfig
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.stereotype.Service

@Service
class MqttClientService(private val mqttConfig: MqttConfig) {
    private val mqttClient = MqttClient(mqttConfig.broker, mqttConfig.clientId)
    val logger = Logger(this::class.java)
    fun connect(): MqttClient {
        if (mqttClient.isConnected) {
            return mqttClient
        }

        val connOpts = MqttConnectOptions()

        connOpts.isCleanSession = true
        connOpts.isAutomaticReconnect = true
        connOpts.connectionTimeout = 10
        connOpts.userName = mqttConfig.username
        connOpts.password = mqttConfig.password.toCharArray()
        mqttClient.connect(connOpts)

        if (mqttClient.isConnected) {
            logger.info(LogDetails(message = "Successfully connected with mqtt"))
        } else {
            logger.info(LogDetails(message = "Failed to connect with mqtt"))
        }


        return mqttClient
    }
}
