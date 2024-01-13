package com.shiviraj.iot.mqtt.model

import java.time.LocalDateTime

data class CommunicationMessage(
    val status: AuditStatus,
    val userId: String,
    val metadata: Map<String, Any>,
    val timestamp: LocalDateTime = LocalDateTime.now()
) : Message()
