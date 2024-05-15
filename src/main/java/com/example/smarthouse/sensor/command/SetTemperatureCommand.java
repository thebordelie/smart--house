package com.example.smarthouse.sensor.command;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.message.SensorMessage;
import com.example.smarthouse.sensor.temperature.TemperatureSensor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SetTemperatureCommand implements Command {
    private final double temperature;

    @Override
    public SensorMessage execute(DefaultSensor sensor) {
        if (sensor instanceof TemperatureSensor) {
            TemperatureSensor temperatureSensor = (TemperatureSensor) sensor;
            temperatureSensor.setTemperature(temperature);
            return new SensorMessage(
                    temperatureSensor.getSensorId(),
                    (int) temperature,
                    null,
                    null,
                    "Temperature set to " + temperature + " °C",
                    null
            );
        } else {
            throw new IllegalArgumentException("Sensor is not a temperature sensor");
        }
    }
}
