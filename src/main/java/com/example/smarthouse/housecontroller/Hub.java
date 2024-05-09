package com.example.smarthouse.housecontroller;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.command.CommandType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Hub {

    void addNewSensor(DefaultSensor sensor);

    void executeCommand(int sensorId, CommandType command);

    List<DefaultSensor> getSensorsInfo();
}
