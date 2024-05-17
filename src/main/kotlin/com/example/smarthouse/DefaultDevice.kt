package com.example.smarthouse.device

import com.example.command.CommandType
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import java.util.Date

abstract class DefaultDevice(
    private val deviceId: Int,
    private val deviceName: String?,
    private var isOn: Boolean
) {
    open val dataFromSensor: Message
        get() = Message(
            deviceId,
            if (isOn) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (isOn) "$deviceName is ON" else "$deviceName is OFF",
            SensorType.DEVICE_STATE
        )

    fun turnOn() {
        isOn = true
        println("$deviceName turned on")
    }

    fun turnOff() {
        isOn = false
        println("$deviceName turned off")
    }

    fun isOn(): Boolean {
        return isOn
    }

    fun getDeviceId(): Int {
        return deviceId
    }

    fun getDeviceName(): String? {
        return deviceName
    }

    open fun executeCommand(command: CommandType, parameters: Any? = null): Message? {
        // Здесь можно реализовать выполнение команды
        return null
    }
}
