package com.example.smarthouse.sensor.smoke;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;

@Data
public class SmokeDetectorSensor extends DefaultSensor {
    private boolean smokeDetected;

    public SmokeDetectorSensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.SMOKE_DETECTOR_SENSOR);
        this.smokeDetected = false;
    }

    public void detectSmoke() {
        smokeDetected = true;
        System.out.println("Smoke detected");
        activateAlarm(); // Activate alarm when smoke is detected
    }

    public void clearSmoke() {
        smokeDetected = false;
        System.out.println("Smoke cleared");
    }

    private void activateAlarm() {
     // Calling an alarm from commands
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                smokeDetected ? 1 : 0,
                new Date(),
                MessageType.SENSOR_DATA,
                smokeDetected ? "Smoke detected" : "No smoke",
                getSensorType()
        );
    }
}
