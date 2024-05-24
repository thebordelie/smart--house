package com.example.smarthouse.sensor.coffeemachine

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.config.DeviceType
import com.example.smarthouse.device.DefaultDevice
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class CoffeeMachine(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultDevice(sensorId, sensorName, false, DeviceType.COFFEE_MACHINE) {

    private var isOn = false

    override val dataFromSensor: Message
        get() = Message(
            sensorId,
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "Coffee Machine is ON" else "Coffee Machine is OFF",
            SensorType.COFFEE_MACHINE
        )
}
