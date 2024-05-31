package com.example.service

import com.example.model.Device
import com.example.model.User
import com.example.model.UserEmulator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Optional

class UserService {
    var users: HashMap<String, ArrayList<Int>> = HashMap()
    var requestGenerator: Generator = RequestGenerator()


    fun testServices() = runBlocking {
        for (userId in 0..10000) {
            launch {
                createUserTest()
            }
        }
    }

    private suspend fun createUserTest() {
        val token = requestGenerator.createNewUser()
        users[token] = ArrayList()
        for (device in 5..(6..15).random()) {
            users[token]?.add(requestGenerator.createNewDevice(token))
        }
        for (getRequest in 5..(6..15).random())
            requestGenerator.sendRandomGetRequest(token, Optional.of(
            users[token]?.get((0..(users[token]?.size ?: 0)).random()) ?: 0
        ))
    }
}