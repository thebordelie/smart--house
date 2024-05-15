package com.example.smarthouse.sensor.smartPower;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;


public class SmartPowerSocketSensor extends DefaultSensor {
    private boolean powerOn;

    public SmartPowerSocketSensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.OFF, sensorProtocol, SensorType.SMART_POWER_SOCKET_SENSOR);
    }

    public void switchPower() {
        powerOn = !powerOn;
        if (powerOn) {
            setSensorState(SensorState.ON);
            System.out.println("Power turned ON");
        } else {
            setSensorState(SensorState.OFF);
            System.out.println("Power turned OFF");
        }
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                powerOn ? 1 : 0,
                new Date(),
                MessageType.SENSOR_DATA,
                powerOn ? "Power is ON" : "Power is OFF",
                getSensorType()
        );
    }
}
