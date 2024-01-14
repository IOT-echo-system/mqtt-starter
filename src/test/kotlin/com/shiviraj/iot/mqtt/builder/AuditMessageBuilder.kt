package com.shiviraj.iot.mqtt.builder

import com.shiviraj.iot.mqtt.model.AuditEvent
import com.shiviraj.iot.mqtt.model.AuditMessage
import com.shiviraj.iot.mqtt.model.AuditStatus
import java.time.LocalDateTime

data class AuditMessageBuilder(
    val status: AuditStatus = AuditStatus.SUCCESS,
    val userId: String = "",
    val metadata: Map<String, Any> = mapOf(),
    val event: AuditEvent = AuditEvent.RESET_PASSWORD,
    val accountId: String? = "",
    val deviceId: String? = "",
    val timestamp: LocalDateTime = LocalDateTime.of(2024, 1, 1, 1, 1)
) {
    fun build(): AuditMessage {
        return AuditMessage(
            status = status,
            userId = userId,
            metadata = metadata,
            event = event,
            accountId = accountId,
            deviceId = deviceId,
            timestamp = timestamp
        )
    }
}
