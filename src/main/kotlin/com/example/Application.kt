package com.example

import com.example.service.UserService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


suspend fun main(args: Array<String>) {
    UserService().testServices()

}

