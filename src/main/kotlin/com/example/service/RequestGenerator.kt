package com.example.service

import com.example.model.Device
import com.example.model.User
import com.example.request.Request
import com.example.request.Requester
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Optional
import kotlin.random.Random

class RequestGenerator : Generator {


    private val deviceTypes = listOf(
        "teapot",
        "humidifier",
        "air_conditioner",
        "vacuum_cleaner",
        "coffee_machine",
        "room_heating"
    )


    var request: Request = Requester()

    private fun getRandomDeviceType(): String {
        return deviceTypes[Random.nextInt(deviceTypes.size)]
    }

    private fun generateRandomString(length: Int): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override suspend fun createNewUser(): String {
        var UserName = generateRandomString(8);
        var Password = generateRandomString(12);
        val body = Json.encodeToString(User(UserName, Password))
        val response = request.post("registration", body);
        return response.toString()
    }

    override suspend fun createNewDevice(token: String): Int {
        val deviceType = getRandomDeviceType()
        val response = request.post("device", deviceType, token) as HttpResponse
        val responseBody = response.bodyAsText()
        val deviceResponse = Json.decodeFromString<Device>(responseBody)
        return deviceResponse.id
    }


    override suspend fun sendRandomGetRequest(token: String, id: Optional<Int>) {
        try {
            val randomChoice = Random.nextInt(3)
            val url = when (randomChoice) {
                0 -> "device"
                1 -> "device/${id.orElse(0)}"
                2 -> "device/info/${id.orElse(0)}"
                else -> throw IllegalStateException("Unexpected value: $randomChoice")
            }
            request.get(url, token, id);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}