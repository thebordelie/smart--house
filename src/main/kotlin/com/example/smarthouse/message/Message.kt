package com.example.message

import com.example.config.SensorType
import java.util.*


class Message(
    val sensorId: Int,
    val sensorData: Int,
    val messageDate: Date,
    val messageType: MessageType,
    val description: String?,
    sensorType: SensorType
) {
    val sensorType: SensorType

    init {
        this.sensorType = sensorType
    }
}

