package com.example.smarthouse.sensor;

import com.example.smarthouse.sensor.config.SensorState;
import lombok.Data;

@Data
public class SensorConfiguration {
    private final int sensorId;
    private SensorState sensorState;
    private String name;
}
