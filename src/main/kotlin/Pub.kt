import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPubSub

fun main(args: Array<String>) {
    println("Hello World!1")
    val jedis1 = Jedis("localhost", 6379)

    jedis1.publish("ilya", "get/123");
    jedis1.close()
}