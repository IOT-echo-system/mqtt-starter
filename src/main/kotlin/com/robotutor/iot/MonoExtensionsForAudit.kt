package com.robotutor.iot

import com.robotutor.iot.models.AuditEvent
import com.robotutor.iot.models.AuditMessage
import com.robotutor.iot.models.AuditStatus
import com.robotutor.iot.models.MqttTopicName
import com.robotutor.iot.services.MqttPublisher
import com.robotutor.iot.utils.models.UserAuthenticationData
import reactor.core.publisher.Mono
import reactor.util.context.ContextView
import java.time.LocalDateTime
import java.time.ZoneId


fun <T> Mono<T>.auditOnError(
    event: AuditEvent,
    metadata: Map<String, Any> = emptyMap(),
    userId: String? = null,
    accountId: String? = null,
    deviceId: String? = null,
): Mono<T> {
    return doOnEach { signal ->
        if (signal.isOnError) {
            val mqttPublisher = signal.contextView.get(MqttPublisher::class.java)
            val auditMessage = AuditMessage(
                status = AuditStatus.FAILURE,
                userId = userId ?: getUserId(signal.contextView),
                metadata = metadata,
                event = event,
                accountId = accountId ?: getAccountId(signal.contextView),
                deviceId = deviceId ?: getDeviceId(signal.contextView),
                timestamp = LocalDateTime.now(ZoneId.of("UTC"))
            )
            mqttPublisher.publish(MqttTopicName.AUDIT, auditMessage)
        }
    }
}


fun <T> Mono<T>.auditOnSuccess(
    event: AuditEvent,
    metadata: Map<String, Any> = emptyMap(),
    userId: String? = null,
    accountId: String? = null,
    deviceId: String? = null,
): Mono<T> {
    return doOnEach { signal ->
        if (signal.isOnNext) {
            val mqttPublisher = signal.contextView.get(MqttPublisher::class.java)
            val auditMessage = AuditMessage(
                status = AuditStatus.SUCCESS,
                userId = userId ?: getUserId(signal.contextView),
                metadata = metadata,
                event = event,
                accountId = accountId ?: getAccountId(signal.contextView),
                deviceId = deviceId ?: getDeviceId(signal.contextView),
                timestamp = LocalDateTime.now(ZoneId.of("UTC"))
            )
            mqttPublisher.publish(MqttTopicName.AUDIT, auditMessage)
        }
    }
}

private fun getDeviceId(contextView: ContextView): String? {
    return contextView.getOrDefault("deviceId", "missing-device-id")
}

private fun getAccountId(contextView: ContextView): String? {
    return contextView.getOrDefault("accountId", "missing-account-id")
}

private fun getUserId(contextView: ContextView): String {
    try {
        val userAuthenticationData = contextView.get(UserAuthenticationData::class.java)
        return userAuthenticationData.userId
    } catch (ex: Exception) {
        return "missing-user-id"
    }
}
