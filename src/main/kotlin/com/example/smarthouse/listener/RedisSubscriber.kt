package com.example.smarthouse.listener

import com.example.smarthouse.publisher.RedisPublisher
import com.example.smarthouse.service.HouseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub
import java.util.*

class RedisSubscriber(host: String, port: Int) {

    private var listener: Jedis;
    private var houseService: HouseService = HouseService();
    private var publisher: RedisPublisher = RedisPublisher("localhost", 6379);
    private val jedisPubSub = object : JedisPubSub() {
        override fun onPMessage(pattern: String, channel: String, message: String) = runBlocking {
            launch {
                println(message)
                val response = houseService.handleRequest(message, channel)
                println(response)
                if (response != null) {
                    publisher.pubResponse(response.uid, response.status, response.data)
                    publisher.pubLog(message.split("/")[0], Date())
                }
            }
            println("message $message")
        }
    }

    init {
        this.listener = Jedis(host, port)
    }

    fun start() {
        listener.psubscribe(jedisPubSub, "uid_*", "create_controller")
    }

    fun stop() {
        listener.close()
    }
}