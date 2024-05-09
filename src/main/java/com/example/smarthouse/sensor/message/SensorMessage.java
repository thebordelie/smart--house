package com.example.smarthouse.sensor.message;

import com.example.smarthouse.sensor.config.SensorType;

import java.util.Date;

public record SensorMessage(int sensorId,
                            int sensorData,
                            Date messageDate,
                            MessageType messageType,
                            String description,
                            SensorType sensorType) {
}
