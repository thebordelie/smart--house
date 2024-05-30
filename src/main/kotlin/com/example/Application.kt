package com.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


suspend fun main(args: Array<String>) {
    val client = HttpClient(CIO)
    val response = client.post("http://localhost:8080/auth/login") {
        contentType(ContentType.Application.Json)
        setBody("{\"login\":\"user\"}")
    }

}

