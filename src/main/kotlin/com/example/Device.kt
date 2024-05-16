package com.example.smarthouse.device

interface Device {
    fun turnOn()
    fun turnOff()
    val isOn: Boolean
}
