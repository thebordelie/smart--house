import com.example.config.SensorProtocol
import com.example.config.SensorState
import com.example.config.SensorType
import com.example.message.MessageType
import com.example.message.Message
import com.example.smarthouse.sensor.DefaultSensor
import java.util.*

class SecuritySensor(sensorId: Int, sensorName: String?, sensorProtocol: SensorProtocol?) :
    DefaultSensor(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.SECURITY_SENSOR) {
    private var alarmActivated = false
    fun activateAlarm() {
        alarmActivated = true
        println("Alarm activated")
    }

    fun deactivateAlarm() {
        alarmActivated = false
        println("Alarm deactivated")
    }

    override val dataFromSensor: Message
        get() = Message(
            getSensorId(),
            if (alarmActivated) 1 else 0,
            Date(),
            MessageType.SENSOR_DATA,
            if (alarmActivated) "Alarm activated" else "Alarm deactivated",
            getSensorType()
        )
}