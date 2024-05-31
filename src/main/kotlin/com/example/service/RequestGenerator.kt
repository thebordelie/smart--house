package com.example.service

import com.example.request.Request
import com.example.request.Requester
import java.util.Optional

class RequestGenerator : Generator {

    var request: Request = Requester()


    override suspend fun createNewUser(): String {
        TODO("Not yet implemented")
    }

    override suspend fun createNewDevice(token: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun sendRandomGetRequest(token: String, id: Optional<Int>) {
        TODO("Not yet implemented")
    }
}