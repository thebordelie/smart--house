package com.example.smarthouse.listener

import kotlinx.coroutines.*
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub

class RedisSubscriber(host: String, port: Int) {

    private var listener: Jedis;
    private val jedisPubSub = object : JedisPubSub() {
        override fun onMessage(channel: String, message: String) {
            val request = message.split("/")

            println("message $message")
        }
    }

    init {
        this.listener = Jedis(host, port)
    }

    fun start() = runBlocking {
        launch(Dispatchers.IO) { subscribeToChannels() }
    }

    private suspend fun subscribeToChannels() {
        withContext(Dispatchers.IO) {
            try {
                listener.subscribe(jedisPubSub, "*")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                listener.close()
            }
        }
    }


}