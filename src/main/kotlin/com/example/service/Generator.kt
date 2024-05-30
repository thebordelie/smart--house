package com.example.service

import com.example.request.Request
import com.example.request.Requester
import io.ktor.client.HttpClient

interface Generator {

    suspend fun createNewUser(): String

    suspend fun createNewDevice(): Int

    suspend fun sendRandomGetRequest()
}