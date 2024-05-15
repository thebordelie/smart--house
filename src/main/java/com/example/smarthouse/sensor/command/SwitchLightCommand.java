package com.example.smarthouse.sensor.command;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.light.LightSensor;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SwitchLightCommand implements Command {
    @Override
    public SensorMessage execute(DefaultSensor sensor) {
        if (sensor instanceof LightSensor) {
            LightSensor lightSensor = (LightSensor) sensor;
            lightSensor.switchLight();
            return new SensorMessage(
                    lightSensor.getSensorId(),
                    lightSensor.isLightOn() ? 1 : 0,
                    null,
                    null,
                    "Light is " + (lightSensor.isLightOn() ? "ON" : "OFF"),
                    null
            );
        } else {
            throw new IllegalArgumentException("Sensor is not a light sensor");
        }
    }
}
