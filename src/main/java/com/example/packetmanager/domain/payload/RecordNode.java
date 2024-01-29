package com.example.packetmanager.domain.payload;

import com.example.packetmanager.domain.payload.record.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Модель, хранящая все структуры Payload, которые были в примере.
 * Названия структур и их индексы были написаны в RecordProviderImpl.
 */
@Getter
@Setter
@ToString
public class RecordNode {

    private Telemetry telemetry;
    private BasicLocationData basicLocationData;
    private DigitalInputsBk digitalInputsBk;
    private FuelLevelSensor fuelLevelSensor;
    private GsmConnection gsmConnection;
    private InputsStatusAdc inputsStatusAdc;
    private Mileage mileage;
    private PowerBk powerBk;
    private TemperatureFuelLevelSensor temperatureFuelLevelSensor;

}
