package com.example.smarthouse.sensor.humidifier

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class Humidifier(val sensorId: Int,val sensorName: String?,val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.HUMIDIFIER) {

    private var isOn = false

    fun turnOn() {
        isOn = true
        println("Humidifier turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Humidifier turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Humidifier is ON" else "Humidifier is OFF",
            getSensorType()
        )
}
