package com.example.housecontroller

import com.example.command.CommandType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor
import com.example.smarthouse.device.DefaultDevice
import java.util.HashMap

class MainController : HubInterface {
    private val hubId = 0
    private val ownerId = 0
    private val sensors: HashMap<Int, DefaultSensor> = HashMap()
    private val devices: HashMap<Int, DefaultDevice> = HashMap()

    override fun addNewSensor(sensor: DefaultSensor?) {
        if (sensor != null) {
            sensors[sensor.getSensorId()] = sensor
        }
    }

    fun addNewDevice(device: DefaultDevice?) {
        if (device != null) {
            devices[device.getDeviceId()] = device
        }
    }

    override fun executeCommand(sensorId: Int, command: CommandType?, parameters: Any?): Message? {
        val sensor: DefaultSensor? = sensors[sensorId]
        val device: DefaultDevice? = devices[sensorId]

        return when {
            sensor != null && command != null -> sensor.executeCommand(command, parameters)
            device != null && command != null -> device.executeCommand(command, parameters)
            else -> null
        }
    }

    override val sensorsInfo: List<Any?>
        get() = sensors.values.toList()
}
