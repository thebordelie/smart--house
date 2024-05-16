package com.example.device

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class Light(val sensorId: Int,val sensorName: String?,val sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.SMART_LIGHT) {
    var lightOn = false
    fun switchLight() {
        lightOn = !lightOn
        if (lightOn) {
            setSensorState(SensorState.ON)
            println("Light turned ON")
        } else {
            setSensorState(SensorState.OFF)
            println("Light turned OFF")
        }
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (lightOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (lightOn) "Light is ON" else "Light is OFF",
            getSensorType()
        )
}
