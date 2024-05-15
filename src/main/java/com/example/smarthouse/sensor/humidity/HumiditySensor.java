package com.example.smarthouse.sensor.humidity;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;

public class HumiditySensor extends DefaultSensor {
    private boolean leakDetected;

    public HumiditySensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.HUMIDITY_SENSOR);
        this.leakDetected = false;
    }

    public void detectLeak() {
        leakDetected = true;
        System.out.println("Leak detected");
        activateAlarm(); // Activate alarm when leak is detected
    }

    public void clearLeak() {
        leakDetected = false;
        System.out.println("Leak cleared");
    }

    private void activateAlarm() {
   // Calling an alarm from commands
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                leakDetected ? 1 : 0,
                new Date(),
                MessageType.SENSOR_DATA,
                leakDetected ? "Leak detected" : "No leak",
                getSensorType()
        );
    }
}
