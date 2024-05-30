package com.example.request

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import java.util.Optional

class Requester: Request {
    var client: HttpClient = HttpClient(CIO)
    var base_url = "http://localhost:8080"


    override suspend fun get(url: String, id: Optional<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun post(url: String, body: String): Any {
        TODO("Not yet implemented")
    }

}