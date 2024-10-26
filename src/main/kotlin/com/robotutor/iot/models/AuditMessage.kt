package com.robotutor.iot.models

import java.time.LocalDateTime
import java.time.ZoneId

data class AuditMessage(
    val status: AuditStatus,
    val userId: String,
    val metadata: Map<String, Any>,
    val event: AuditEvent,
    val accountId: String? = null,
    val deviceId: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC"))
) : Message()

enum class AuditStatus {
    SUCCESS,
    FAILURE
}

enum class AuditEvent {
    SIGN_UP,
    VERIFY_PASSWORD,
    LOGIN,
    GENERATE_TOKEN,
    GENERATE_OTP,
    VERIFY_OTP,
    RESET_PASSWORD,
    SEND_EMAIL,
    CREATE_ACCOUNT,
    LOG_OUT,
    CREATE_BOARD,
    UPDATE_BOARD,
    ADD_WIDGET,
    UPDATE_WIDGET_TITLE,
    ADD_INVOICE_WIDGET_SEED,
    UPDATE_INVOICE_WIDGET_SEED,
    UPDATE_INVOICE_WIDGET_ITEM,
    UPDATE_INVOICE_PAYMENT,
    COLLECTION_OF_BUTTONS_ADD_BUTTON,
    COLLECTION_OF_BUTTONS_UPDATE_BUTTON,
    COLLECTION_OF_BUTTONS_DELETE_BUTTON,
    COLLECTION_OF_BUTTONS_UPDATE_BUTTON_VALUE,
    COLLECTION_OF_BUTTONS_UPDATE_SENSOR_VALUE,
    UPDATE_ACCOUNT_NAME,
    CREATE_ROUTINE,
    UPDATE_ROUTINE,
    UPDATE_ROUTINE_NAME,
    UPDATE_LEVEL_MONITOR_WIDGET_VALUES,
    CAPTURE_LEVEL_MONITOR_WIDGET_VALUE,
    LEVEL_MONITOR_WIDGET_SENSOR_VALUE_UPDATE,
}
