package com.example.command

import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor


interface Command {
    fun execute(sensorD: DefaultSensor?): Message?
}

