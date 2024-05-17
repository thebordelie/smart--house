package com.example.smarthouse.sensor.teapot

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class Teapot(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.TEAPOT) {

    private var isOn = false

    fun turnOn() {
        isOn = true
        println("Teapot turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Teapot turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            sensorId,
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Teapot is ON" else "Teapot is OFF",
            SensorType.TEAPOT
        )
}
