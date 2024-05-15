package com.example.smarthouse.sensor;

import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;

public class SecuritySensor extends DefaultSensor {
    private boolean alarmActivated;

    public SecuritySensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.SECURITY_SENSOR);
        this.alarmActivated = false;
    }

    public void activateAlarm() {
        alarmActivated = true;
        System.out.println("Alarm activated");
    }

    public void deactivateAlarm() {
        alarmActivated = false;
        System.out.println("Alarm deactivated");
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                alarmActivated ? 1 : 0,
                new Date(),
                MessageType.SENSOR_DATA,
                alarmActivated ? "Alarm activated" : "Alarm deactivated",
                getSensorType()
        );
    }
}
