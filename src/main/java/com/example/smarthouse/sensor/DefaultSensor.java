package com.example.smarthouse.sensor;

import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import lombok.Data;

@Data
public abstract class DefaultSensor implements Sensor{
    private final int sensorId;
    private final String sensorName;
    private SensorState sensorState;
    private final SensorProtocol sensorProtocol;
    private final SensorType sensorType;
}
