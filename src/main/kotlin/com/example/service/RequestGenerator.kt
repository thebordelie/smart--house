package com.example.service

import com.example.request.Request
import com.example.request.Requester

class RequestGenerator : Generator {

    var request: Request = Requester()


    override suspend fun createNewUser(): String {
        TODO("Not yet implemented")
    }

    override suspend fun createNewDevice(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun sendRandomGetRequest() {
        TODO("Not yet implemented")
    }
}