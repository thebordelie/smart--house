package com.example.request

interface ExtendRequest: Request {

    suspend fun postWithToken(url: String, body: String, token: String): Any
}