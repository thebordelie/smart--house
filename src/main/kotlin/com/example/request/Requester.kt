package com.example.request

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

class Requester {
    var client: HttpClient = HttpClient(CIO)
    var base_url = "http://localhost:8080"

    fun getDevice () {

    }

}