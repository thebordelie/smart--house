package com.example.smarthouse.sensor.coffeemachine

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

class CoffeeMachine(sensorId: Int, sensorName: String?, sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.COFFEE_MACHINE) {

    private var isOn = false

    fun turnOn() {
        isOn = true
        println("Coffee Machine turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Coffee Machine turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Coffee Machine is ON" else "Coffee Machine is OFF",
            getSensorType()
        )
}
