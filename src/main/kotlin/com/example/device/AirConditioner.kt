package com.example.smarthouse.sensor.airconditioner

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import com.example.smarthouse.sensor.temperature.TemperatureSensor
import java.util.*

class AirConditioner(sensorId: Int, sensorName: String?, sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.AIR_CONDITIONER) {

    private var isOn = false
    var desiredTemperature: Double = 20.0
        private set

    private var temperatureSensor: TemperatureSensor? = null

    fun decreaseTemperature(currentTemperature: Double) {
        if (!isOn) {
            turnOn()
        }
        if (currentTemperature > desiredTemperature) {
            timer?.cancel()
            timer = Timer()
            timer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (desiredTemperature < currentTemperature) {
                        desiredTemperature++
                        temperatureSensor?.setCurrentTemperature(desiredTemperature)
                    } else {
                        timer?.cancel()
                    }
                }
            }, 0, 60 * 1000)
        } else {
            turnOff()
        }
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
        println("Air Conditioner turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Air Conditioner turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Air Conditioner is ON" else "Air Conditioner is OFF",
            getSensorType()
        )
}
