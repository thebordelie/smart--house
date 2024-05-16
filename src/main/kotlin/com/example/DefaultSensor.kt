package com.example.smarthouse.sensor

import com.example.command.CommandType
import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import java.util.*

abstract class DefaultSensor(
    private val sensorId: Int,
    private val sensorName: String?,
    private var sensorState: SensorState,
    private val sensorProtocol: SensorProtocol?,
    private val sensorType: SensorType
) {
    open val dataFromSensor: Message
        get() = Message(
            sensorId,
            0,
            Date(),
            MessageType.SENSOR_DATA,
            null,
            sensorType
        )

    fun getSensorId(): Int {
        return sensorId
    }

    fun getSensorName(): String? {
        return sensorName
    }

    fun getSensorState(): SensorState {
        return sensorState
    }

    fun setSensorState(sensorState: SensorState) {
        this.sensorState = sensorState
    }

    fun getSensorProtocol(): SensorProtocol? {
        return sensorProtocol
    }

    fun getSensorType(): SensorType {
        return sensorType
    }

    open fun executeCommand(command: CommandType, parameters: Any? = null): Message? {
        // Здесь можно реализовать выполнение команды
        return null
    }
}



