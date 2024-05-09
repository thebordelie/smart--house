package com.example.smarthouse.housecontroller;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.command.Command;
import com.example.smarthouse.sensor.command.CommandType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MainController implements Hub {
    private final int hubId;
    private final int ownerId;
    private HashMap<Integer, DefaultSensor> sensors;
    private HashMap<CommandType, Command> commands;

    @Override
    public void addNewSensor(DefaultSensor sensor) {
        sensors.put(sensor.getSensorId(), sensor);
    }

    @Override
    public void executeCommand(int sensorId, CommandType command) {
        commands.get(command).execute(sensors.get(sensorId));
    }

    @Override
    public List<DefaultSensor> getSensorsInfo() {
        return sensors.values().stream().collect(Collectors.toList());
    }

}
