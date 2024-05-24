package com.example.smarthouse.sensor.teapot

import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.Message
import com.example.message.MessageType
import com.example.smarthouse.config.DeviceType
import com.example.smarthouse.device.DefaultDevice
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

data class Teapot(val sensorId: Int, val sensorName: String?, val sensorProtocol: SensorProtocol?) :
    DefaultDevice(sensorId, sensorName, false, DeviceType.TEAPOT) {

    private var isOn = false

}
