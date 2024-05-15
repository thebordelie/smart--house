package com.example.smarthouse.sensor.command;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.SecuritySensor;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeactivateAlarmCommand implements Command {
    @Override
    public SensorMessage execute(DefaultSensor sensor) {
        if (sensor instanceof SecuritySensor) {
            SecuritySensor securitySensor = (SecuritySensor) sensor;
            securitySensor.deactivateAlarm();
            return new SensorMessage(
                    securitySensor.getSensorId(),
                    0,
                    null,
                    null,
                    "Alarm deactivated",
                    null
            );
        } else {
            throw new IllegalArgumentException("Sensor is not a security sensor");
        }
    }
}
