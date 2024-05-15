package com.example.smarthouse.sensor.temperature;

import com.example.smarthouse.sensor.DefaultSensor;
import com.example.smarthouse.sensor.config.SensorProtocol;
import com.example.smarthouse.sensor.config.SensorState;
import com.example.smarthouse.sensor.config.SensorType;
import com.example.smarthouse.sensor.message.MessageType;
import com.example.smarthouse.sensor.message.SensorMessage;
import lombok.Data;

import java.util.Date;
import java.util.Random;

public class TemperatureSensor extends DefaultSensor {
    private double temperature;
    private double outdoorTemperature;
    private boolean heaterOn;

    public TemperatureSensor(int sensorId, String sensorName, SensorProtocol sensorProtocol) {
        super(sensorId, sensorName, SensorState.ON, sensorProtocol, SensorType.TEMPERATURE_SENSOR);
        this.temperature = 20.0; // Default temperature
        this.outdoorTemperature = generateRandomOutdoorTemperature();
        this.heaterOn = false;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Indoor temperature set to " + temperature + " °C");
        manageHeater(); // Check if heater needs to be turned on or off
    }

    public void setOutdoorTemperature(double outdoorTemperature) {
        this.outdoorTemperature = outdoorTemperature;
        System.out.println("Outdoor temperature set to " + outdoorTemperature + " °C");
        manageHeater(); // Check if heater needs to be turned on or off
    }

    private void manageHeater() {
        if (temperature < 18.0 && outdoorTemperature < 10.0) {
            heaterOn = true;
            System.out.println("Heater turned ON");
        } else {
            heaterOn = false;
            System.out.println("Heater turned OFF");
        }
    }

    private double generateRandomOutdoorTemperature() {
        // Generate random outdoor temperature between -10°C and 30°C
        Random random = new Random();
        return -10.0 + random.nextDouble() * 40.0;
    }

    @Override
    public SensorMessage getDataFromSensor() {
        return new SensorMessage(
                getSensorId(),
                (int) temperature,
                new Date(),
                MessageType.SENSOR_DATA,
                "Indoor temperature is " + temperature + " °C",
                getSensorType()
        );
    }
}
