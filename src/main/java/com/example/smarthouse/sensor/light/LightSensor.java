package com.example.smarthouse.sensor.light;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;

public class LightSensor extends DefaultSensor {
    private boolean lightOn;

    public LightSensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.SMART_LIGHT);
    }

    public void switchLight() {
        lightOn = !lightOn;
        if (lightOn) {
            setSensorState(SensorState.ON);
            System.out.println("Light turned ON");
        } else {
            setSensorState(SensorState.OFF);
            System.out.println("Light turned OFF");
        }
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                lightOn ? 1 : 0,
                new Date(),
                MessageType.SENSOR_DATA,
                lightOn ? "Light is ON" : "Light is OFF",
                getSensorType()
        );
    }
}
