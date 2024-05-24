package com.example.smarthouse.service

import SecuritySensor
import com.example.command.CommandType
import com.example.config.SensorProtocol
import com.example.device.Light
import com.example.housecontroller.MainController
import com.example.smarthouse.device.DefaultDevice
import com.example.smarthouse.message.Response
import com.example.smarthouse.sensor.DefaultSensor
import com.example.smarthouse.sensor.airconditioner.AirConditioner
import com.example.smarthouse.sensor.coffeemachine.CoffeeMachine
import com.example.smarthouse.sensor.humidifier.Humidifier
import com.example.smarthouse.sensor.humidity.HumiditySensor
import com.example.smarthouse.sensor.roomheating.RoomHeating
import com.example.smarthouse.sensor.smoke.SmokeDetectorSensor
import com.example.smarthouse.sensor.teapot.Teapot
import com.example.smarthouse.sensor.vacuumcleaner.VacuumCleaner

class HouseService {
    private var controllers: HashMap<String, MainController>

    constructor() {
        this.controllers = HashMap()
    }


    suspend fun handleRequest(request: String, channel: String): Response? {
        val requestParams = request.split("/")
        println(requestParams)
        if (channel == "create_controller") {
            var uid = requestParams[0]
            var cid = Integer.parseInt(requestParams[1])
            var name = requestParams[2]
            if (controllers[uid] == null) {
                controllers[uid] = MainController()
            }
            createDevice(name, cid)?.let { device ->
                controllers[channel]?.addNewDevice(device)
            }
            createSensor(name, cid)?.let { sensor ->
                controllers[channel]?.addNewSensor(sensor)
            }
            return Response(channel, "ok", "")
        }
        return when (requestParams[0]) {
            "get" -> controllers[channel]?.let {
                return Response(
                    channel,
                    "ok",
                    it.getInfo(Integer.parseInt(requestParams[1]))
                )
            }
            "set" -> {
                controllers[channel]?.executeCommand(
                    Integer.parseInt(requestParams[1]),
                    CommandType.CHANGE_CONFIG,
                    requestParams[2]
                )
                return Response("uid", "ok", "")
            }
            "on" -> {
                controllers[channel]?.executeCommand(Integer.parseInt(requestParams[1]), CommandType.SWITCH_SENSOR)
                return Response("uid", "ok", "")
            }
            "off" -> {
                controllers[channel]?.executeCommand(Integer.parseInt(requestParams[1]), CommandType.SWITCH_SENSOR)
                return Response("uid", "ok", "")
            }
            else -> Response(channel, "error", "/Unknown operation")
        }
    }

    private fun createDevice(deviceType: String, cid: Int): DefaultDevice? {
        return when (deviceType) {
            "teapot" -> Teapot(cid, deviceType, SensorProtocol.ZWAVE)
            "humidifier" -> Humidifier(cid, deviceType, SensorProtocol.ZWAVE)
            "air_conditioner" -> AirConditioner(cid, deviceType, SensorProtocol.ZWAVE)
            "vacuum_cleaner" -> VacuumCleaner(cid, deviceType, SensorProtocol.ZWAVE)
            "coffee_machine" -> CoffeeMachine(cid, deviceType, SensorProtocol.ZWAVE)
            "room_heating" -> RoomHeating(cid, deviceType, SensorProtocol.ZWAVE)
            else -> null
        }
    }

    private var sensorIdCounter = 0
    private var deviceIdCounter = 0

    private fun generateSensorId(): Int {
        return ++sensorIdCounter
    }

    private fun generateDeviceId(): Int {
        return ++deviceIdCounter
    }


    private fun createSensor(sensorType: String, cid: Int): DefaultSensor? {
        return when (sensorType) {
            "water_sensor" -> HumiditySensor(cid, sensorType, SensorProtocol.ZWAVE)
            "light_sensor" -> Light(cid, sensorType, SensorProtocol.ZWAVE)
            "security_sensor" -> SecuritySensor(cid, sensorType, SensorProtocol.ZWAVE)
            "smoke_sensor" -> SmokeDetectorSensor(cid, sensorType, SensorProtocol.ZWAVE)
            else -> null
        }
    }


}