package com.example.smarthouse.sensor.smoke

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor

import java.util.*

data class SmokeDetectorSensor(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.SMOKE_DETECTOR_SENSOR) {
    private var smokeDetected = false
    fun detectSmoke() {
        smokeDetected = true
        println("Smoke detected")
        activateAlarm() // Activate alarm when smoke is detected
    }

    fun clearSmoke() {
        smokeDetected = false
        println("Smoke cleared")
    }

    private fun activateAlarm() {
        // Calling an alarm from commands
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (smokeDetected) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (smokeDetected) "Smoke detected" else "No smoke",
            getSensorType()
        )
}
