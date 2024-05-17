package com.example.smarthouse.listener

import com.example.smarthouse.service.HouseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub

class RedisSubscriber(host: String, port: Int) {

    private var listener: Jedis;
    private var houseService: HouseService = HouseService();
    private val jedisPubSub = object : JedisPubSub() {
        override fun onPMessage(pattern: String, channel: String, message: String) = runBlocking {
            launch {
                val response = houseService.handleRequest(message, channel)


            }
            println("message $message")
        }
    }
    init {
        this.listener = Jedis(host, port)
    }

    fun start() {
        listener.psubscribe(jedisPubSub, "*")
    }

    fun stop() {
        listener.close()
    }
}