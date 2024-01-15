package com.shiviraj.iot.mqtt.model

data class CommunicationMessage(
    val type: CommunicationType,
    val to: String,
    val userId: String,
    val metadata: Map<String, Any>
) : Message()

enum class CommunicationType {
    OTP
}
