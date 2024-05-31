package com.example.service

import com.example.request.Request
import com.example.request.Requester
import io.ktor.client.HttpClient
import java.util.Optional

interface Generator {

    suspend fun createNewUser(): String

    suspend fun createNewDevice(token: String): Int

    suspend fun sendRandomGetRequest(token: String, id: Optional<Int>)
}