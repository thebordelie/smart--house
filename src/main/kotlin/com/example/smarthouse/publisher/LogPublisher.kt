package com.example.smarthouse.publisher

import redis.clients.jedis.Jedis
import java.util.Date

class LogPublisher(host: String, port: Int) {
    private var logChannel: String = "log"
    private var publisher: Jedis;

    init {
        this.publisher = Jedis(host, port)
    }


    fun pubLog(action: String, date: Date) {
        publisher.publish(logChannel, String.format("emulator_houses/%s/%s", action, date))
    }
}