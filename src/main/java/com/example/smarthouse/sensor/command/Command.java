package com.example.smarthouse.sensor.command;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.message.SensorMessage;

public interface Command {
    SensorMessage execute(DefaultSensor sensorD);
}
