package com.example.request

import com.example.model.User
import java.util.Optional

interface Request {
    suspend fun get(url: String, id: Optional<Int>)

    suspend fun post(url: String, body: String): Any
}