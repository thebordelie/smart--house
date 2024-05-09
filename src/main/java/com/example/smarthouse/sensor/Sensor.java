package com.example.smarthouse.sensor;

import com.example.smarthouse.sensor.message.SensorMessage;

public interface Sensor {
    SensorMessage getDataFromSensor();

    void switchSensorState();



}
