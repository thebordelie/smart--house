package com.example.smarthouse.sensor.roomheating

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import com.example.smarthouse.sensor.temperature.TemperatureSensor
import java.util.*

data class RoomHeating(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.ROOM_HEATING) {

    private var isOn = false
    var desiredTemperature: Double = 20.0
        private set

    private var temperatureSensor: TemperatureSensor? = null

    fun increaseTemperature(currentTemperature: Double): Double {
        if (!isOn) {
            turnOn()
        }
        if (currentTemperature < desiredTemperature) {
            timer?.cancel()
            timer = Timer()
            timer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (desiredTemperature > currentTemperature) {
                        currentTemperature-1
                    } else {
                        timer?.cancel()
                    }
                }
            }, 0, 60 * 1000)
        } else {
            turnOff()
        }
        return currentTemperature// Увеличиваем текущую температуру
    }

    fun setDesiredTemperature(temperature: Double) {
        desiredTemperature = temperature
    }

    fun setTemperatureSensor(sensor: TemperatureSensor) {
        temperatureSensor = sensor
    }

    private var timer: Timer? = null

    fun turnOn() {
        isOn = true
        println("Room Heating turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Room Heating turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            sensorId,
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Room Heating is ON" else "Room Heating is OFF",
            SensorType.ROOM_HEATING
        )
}
