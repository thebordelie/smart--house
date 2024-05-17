package com.example

import com.example.message.Message


interface Sensor {
    val dataFromSensor: Message?

    fun switchSensorState()
}

