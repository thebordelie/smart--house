package com.example.smarthouse.sensor.humidity

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class HumiditySensor(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.HUMIDITY_SENSOR) {
    private var leakDetected = false
    fun detectLeak() {
        leakDetected = true
        println("Leak detected")
        activateAlarm() // Activate alarm when leak is detected
    }

    fun clearLeak() {
        leakDetected = false
        println("Leak cleared")
    }

    private fun activateAlarm() {
        // Calling an alarm from commands
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (leakDetected) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (leakDetected) "Leak detected" else "No leak",
            getSensorType()
        )
}
