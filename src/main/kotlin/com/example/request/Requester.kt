package com.example.request

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import java.util.Optional

class Requester : Request {
    var client: HttpClient = HttpClient(CIO)
    var base_url = "http://localhost:8080/"

    override suspend fun get(url: String, token: String, id: Optional<Int>) {
        client.get("$base_url$url/$id") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun post(url: String, body: String, vararg token: String): Any {
        val response = client.post("$base_url$url") {
            contentType(ContentType.Application.Json)
            if (token.isNotEmpty()) {
                headers {
                    append(HttpHeaders.Authorization, token[0])
                }
            }
            setBody(body)
        }
        return response.body()
    }

}