package com.example.smarthouse.sensor.vacuumcleaner

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

class VacuumCleaner(sensorId: Int, sensorName: String?, sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.VACUUM_CLEANER) {

    private var isOn = false

    fun turnOn() {
        isOn = true
        println("Vacuum Cleaner turned ON")
    }

    fun turnOff() {
        isOn = false
        println("Vacuum Cleaner turned OFF")
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Vacuum Cleaner is ON" else "Vacuum Cleaner is OFF",
            getSensorType()
        )
}
