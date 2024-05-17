package com.example.smarthouse.service

import com.example.command.CommandType
import com.example.housecontroller.MainController
import com.example.smarthouse.message.Response

class HouseService {
    private var controllers: HashMap<String, MainController>

    constructor() {
        this.controllers = HashMap()
    }


    suspend fun handleRequest(request: String, uid: String): Response? {
        if (uid == "create_controller") {
            if (controllers[uid] == null) {
                controllers[uid] = MainController()
            }
            controllers[uid]
            return Response(uid, "ok", "")
        }
        val requestParams = request.split("/")
        return when (requestParams[0]) {
            "get" -> controllers[uid]?.let { Response(uid, "ok", it.getInfo(Integer.parseInt(requestParams[1]))) }
            "on" -> {
                controllers[uid]?.executeCommand(Integer.parseInt(requestParams[1]), CommandType.SWITCH_SENSOR)
                return Response("uid", "ok", "")
            }
            "off" -> {
                controllers[uid]?.executeCommand(Integer.parseInt(requestParams[1]), CommandType.SWITCH_SENSOR)
                return Response("uid", "ok", "")
            }
            else -> Response(uid, "error", "/Unknown operation")
        }
    }





}