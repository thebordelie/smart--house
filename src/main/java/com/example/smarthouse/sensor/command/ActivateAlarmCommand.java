package com.example.smarthouse.sensor.command;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.SecuritySensor;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivateAlarmCommand implements Command {
    @Override
    public SensorMessage execute(DefaultSensor sensor) {
        if (sensor instanceof SecuritySensor) {
            SecuritySensor securitySensor = (SecuritySensor) sensor;
            securitySensor.activateAlarm();
            return new SensorMessage(
                    securitySensor.getSensorId(),
                    1,
                    null,
                    null,
                    "Alarm activated",
                    null
            );
        } else {
            throw new IllegalArgumentException("Sensor is not a security sensor");
        }
    }
}
