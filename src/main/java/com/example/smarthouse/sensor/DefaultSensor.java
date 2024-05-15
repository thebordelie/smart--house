package com.example.smarthouse.sensor;

import com.example.smarthouse.sensor.Sensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;

@Data
public abstract class DefaultSensor implements Sensor {
    private final int sensorId;
    private final String sensorName;
    private SensorState sensorState;
    private final SensorProtocol sensorProtocol;
    private final SensorType sensorType;

    public DefaultSensor(int sensorId, String sensorName, SensorState sensorState, SensorProtocol sensorProtocol, SensorType sensorType) {
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.sensorState = sensorState;
        this.sensorProtocol = sensorProtocol;
        this.sensorType = sensorType;
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                sensorId,
                0, // Placeholder for sensor data, to be implemented in subclasses
                new Date(),
                MessageType.SENSOR_DATA,
                null,
                sensorType
        );
    }

    @Override
    public void switchSensorState() {
        sensorState = (sensorState == SensorState.ON) ? SensorState.OFF : SensorState.ON;
    }
}
