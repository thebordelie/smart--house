import com.example.command.CommandType
import com.example.config.SensorProtocol
import com.example.smarthouse.listener.RedisSubscriber
import com.example.smarthouse.sensor.airconditioner.AirConditioner
import com.example.smarthouse.sensor.humidity.HumiditySensor
import com.example.smarthouse.sensor.temperature.TemperatureSensor
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub


fun main(args: Array<String>) {
//    println("Hello World!")
//    var listener = RedisSubscriber("localhost", 6379)
//    listener.start()
//    listener.stop()

    var tmp = AirConditioner(1,"hum", SensorProtocol.WIFI)
    println(tmp.getOn())
    println(tmp.executeCommand(CommandType.SWITCH_SENSOR))
    println(tmp.getOn())

}