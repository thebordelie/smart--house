package com.example.smarthouse.sensor.temperature

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import com.example.smarthouse.sensor.airconditioner.AirConditioner
import com.example.smarthouse.sensor.roomheating.RoomHeating
import java.util.*

data class TemperatureSensor(
    val sensorId: Int,
    val sensorName: String?,
    val sensorProtocol: SensorProtocol?,
    private val roomHeating: RoomHeating,
    private val airConditioner: AirConditioner
) : DefaultSensor(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.TEMPERATURE_SENSOR) {

    private var currentTemperature: Double = 20.0
    private val outdoorTemperature: Double
        get() = generateRandomOutdoorTemperature()

    init {
        roomHeating.setTemperatureSensor(this)
        airConditioner.setTemperatureSensor(this)
    }

    fun setCurrentTemperature(temperature: Double) {
        currentTemperature = temperature
        println("Indoor temperature set to $temperature °C")
        manageTemperature()
    }

    fun getCurrentTemperature(): Double {
        return currentTemperature
    }

    private fun manageTemperature() { // TODO тут как-то седлать чтобы обогреватель не грел а
        if (currentTemperature < roomHeating.desiredTemperature) {
            currentTemperature = roomHeating.increaseTemperature(currentTemperature)
        } else if (currentTemperature > airConditioner.desiredTemperature) {
            currentTemperature = airConditioner.decreaseTemperature(currentTemperature)
        } else {
            airConditioner.turnOff()
            roomHeating.turnOff()
        }
    }

    private fun generateRandomOutdoorTemperature(): Double {
        val random = Random()
        return -10.0 + random.nextDouble() * 40.0
    }

    override val dataFromSensor: Message
        get() = Message(
            sensorId,
            currentTemperature.toInt(),
            Date(),
            MessageType.SENSOR_DATA,
            "Indoor temperature is $currentTemperature °C",
            SensorType.TEMPERATURE_SENSOR
        )
}
