package com.example.smarthouse.publisher

import redis.clients.jedis.Jedis
import java.util.Date

class LogPublisher(host: String, port: Int) {
    private var logChannel: String = "log"
    private var alarmChannel: String = "alarm"
    private var publisher: Jedis;

    init {
        this.publisher = Jedis(host, port)
    }


    fun pubLog(action: String, date: Date) {
        publisher.publish(logChannel, "emulator_houses/${action}/${date}")
    }

    fun pubAlarm(uid: String, deviceType: String, event: String) {
        publisher.publish("alarm", "${uid}/${deviceType}/${event}")
    }


}