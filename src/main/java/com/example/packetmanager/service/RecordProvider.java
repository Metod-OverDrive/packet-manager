package com.example.packetmanager.service;

import com.example.packetmanager.domain.payload.RecordNode;
import com.example.packetmanager.domain.payload.record.*;

import java.util.List;

public interface RecordProvider {

    RecordContainer nextRecordContainer(int[] values, int[] baitSteps);
    Telemetry setTelemetry(int[] values, int[] baitSteps, long size);
    GsmConnection setGsmConnection(int[] values, int[] baitSteps, long size);
    PowerBk setPowerBk(int[] values, int[] baitSteps, long size);
    Mileage setMileage(int[] values, int[] baitSteps, long size);
    BasicLocationData setBasicLocationData(int[] values, int[] baitSteps, long size);
    FuelLevelSensor setFuelLevelSensor(int[] values, int[] baitSteps, long size);
    TemperatureFuelLevelSensor setTemperatureFuelLevelSensor(int[] values, int[] baitSteps, long size);
    DigitalInputsBk setDigitalInputsBk(int[] values, int[] baitSteps, long size);
    InputsStatusAdc setInputsStatusAdc(int[] values, int[] baitSteps, long size);

}
