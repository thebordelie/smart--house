package com.example.housecontroller

import com.example.command.CommandType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor

interface HubInterface {
    fun addNewSensor(sensor: DefaultSensor?)
    fun executeCommand(sensorId: Int, command: CommandType?, parameters: Any? = null): Message?
    val sensorsInfo: List<Any?>
}