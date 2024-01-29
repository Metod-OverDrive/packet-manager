package com.example.packetmanager.service.impl;

import com.example.packetmanager.domain.payload.record.*;
import com.example.packetmanager.service.BitOperator;
import com.example.packetmanager.service.RecordProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Здесь расположены методы для обработки PayLoad.
 * В ходе задания были реализованы 9 структур данных,
 * необходимых для анализа полученных данных.
 * Над методами будет написаны их оригинальные названия и индексы структур.
 */

@Service
@RequiredArgsConstructor
public class RecordProviderImpl implements RecordProvider {

    private final BitOperator bitOperator;

    /**
     * Этот метод используется для прохождения по цепочке записей,
     * вытаскивая размер данных в байтах и индекс типа структуры.
     */
    @Override
    public RecordContainer nextRecordContainer(int[] values, int[] baitSteps) {
        RecordContainer recordContainer = new RecordContainer();
        int step = baitSteps[0];

        long temp = bitOperator.convertBait(values, step, 2);
        recordContainer.setSize(temp & 0b1111111111);
        recordContainer.setRecordType((int) (temp & 0b1111110000000000) >> 10);

        baitSteps[0] += 2;

        return recordContainer;
    }

    /**
     * Заголовок сообщения телеметрии - 0
     */
    @Override
    public Telemetry setTelemetry(int[] values, int[] baitSteps, long size) {
        Telemetry telemetry = new Telemetry();
        int step = baitSteps[0];
        int count = 0;

        LocalDateTime dateTime = LocalDateTime.of(2010, 1, 1, 0, 0, 0);
        long seconds = bitOperator.convertBait(values, step, 4);
        telemetry.setMessageTime(dateTime.plusSeconds(seconds));

        count += 4;

        if (size >= 7) {
            telemetry.setMessageIndex(bitOperator.convertBait(values, step + 5, 2));
            telemetry.setMessageCode(values[step + 7]);
            count += 3;
        }
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }

        return telemetry;
    }

    /**
     * Состояние связи GSM - 4
     */
    @Override
    public GsmConnection setGsmConnection(int[] values, int[] baitSteps, long size) {
        GsmConnection gsmConnection = new GsmConnection();
        int step = baitSteps[0];
        int count = 0;


        gsmConnection.setGsmLevelConnection(values[step] & 0b11111);
        gsmConnection.setConnectionSigh(((values[step] >> 5) & 0b1) == 1);
        gsmConnection.setPacketConnectionSigh(((values[step] >> 6) & 0b1) == 1);
        gsmConnection.setConnectionSighWithCc(((values[step] >> 7) & 0b1) == 1);

        count++;

        if (size >= 4) {
            long temp = (bitOperator.convertBait(values, step + 1, 3));
            gsmConnection.setOperatorCode(temp & 0b11111111111111111111);
            gsmConnection.setRoamingRegistrationSigh(((temp >> 20) & 0b1) == 1);
            gsmConnection.setNumberActiveSim(((temp >> 21) & 0b1) == 1);
            gsmConnection.setAdditionalConnectionWithCc(((temp >> 22) & 0b1) == 1);
            gsmConnection.setNumberActivAntennaGsm(((temp >> 23) & 0b1) == 1);
            count += 3;
        }

        if (size >= 10) {
            gsmConnection.setLacGsm(bitOperator.convertBait(values,
                    step + 4, 2));
            gsmConnection.setCellIdGsm(bitOperator.convertBait(values,
                    step + 6, 4));
            count += 6;
        }
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }

        return gsmConnection;
    }

    /**
     * Состояние питания БК - 3
     */
    @Override
    public PowerBk setPowerBk(int[] values, int[] baitSteps, long size) {
        PowerBk powerBk = new PowerBk();
        int step = baitSteps[0];
        int count = 0;

        long temp = (bitOperator.convertBait(values, step, 3));
        powerBk.setBoardVoltage(temp & 0b1111111111111);
        powerBk.setIgnition(((temp >> 14) & 0b1) == 1);
        powerBk.setAkbBkVoltage((temp & 0b111111111100000000000000) >> 14);

        count += 3;

        if (size >= 4) {
            temp = values[step + 3];
            powerBk.setLowVoltageSighBk((((temp) & 0b1) == 1));
            powerBk.setLowVoltageSighAkb((((temp >> 1) & 0b1) == 1));
            powerBk.setEnergyStatus(((int) temp & 0b1100) >> 2);
            powerBk.setEvacuationSigh((((temp >> 4) & 0b1) == 1));
            count += 1;
        }
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }

        return powerBk;
    }

    /**
     * Пробег - 2
     */
    @Override
    public Mileage setMileage(int[] values, int[] baitSteps, long size) {
        Mileage mileage = new Mileage();
        int step = baitSteps[0];
        int count = 0;

        mileage.setMileage(bitOperator.convertBait(values, step, 4));
        count += 4;

        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }

        return mileage;
    }

    /**
     * Основные данные местоположения - 1
     */
    @Override
    public BasicLocationData setBasicLocationData(int[] values, int[] baitSteps, long size) {
        BasicLocationData basicLocationData = new BasicLocationData();
        int step = baitSteps[0];
        int count = 0;
        long temp;

        temp = bitOperator.convertBait(values, step, 4);
        basicLocationData.setWidth(temp > 0 ? temp : temp * (-1));
        temp = bitOperator.convertBait(values, step + 4, 4);
        basicLocationData.setLongitude(temp > 0 ? temp : temp * (-1));

        temp = bitOperator.convertBait(values, step + 8, 2);
        basicLocationData.setSpeed(temp & 0b11111111111111);
        basicLocationData.setHemispheresWidth(((temp >> 14) & 0b1) == 1);
        basicLocationData.setHemispheresLongitude(((temp >> 15) & 0b1) == 1);

        temp = bitOperator.convertBait(values, step + 10, 2);
        basicLocationData.setCourse(temp & 0b111111111);
        basicLocationData.setStopSign(((int) temp & 0b11000000000) >> 9);
        basicLocationData.setSatellites(((int) temp & 0b1111100000000000) >> 11);
        count +=12;

        if (size >= 14) {
            temp = bitOperator.convertBait(values, step + 12, 2);
            basicLocationData.setHigh(temp & 0b11111111111111);
            basicLocationData.setHighSign(((temp >> 14) & 0b1) == 1);
            count +=2;
        }
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }

        return basicLocationData;
    }

    /**
     * Состояния датчиков уровня топлива - 11
     */
    @Override
    public FuelLevelSensor setFuelLevelSensor(int[] values, int[] baitSteps, long size) {
        FuelLevelSensor fuelLevelSensor = new FuelLevelSensor();
        int step = baitSteps[0];
        int count = 0;
        long temp = values[step++];
        int unitCount = 0;

        while (temp != 0) {
            if ((temp & 1) == 1) {
                unitCount++;
            }
            temp = temp >> 1;
        }

        long[] arr = new long[unitCount];
        int j = 0;
        for (int i = 0; i < unitCount; i++) {
            temp = bitOperator.convertBait(values, step + j, 2);
            arr[i] = temp;
            j += 2;
            count += 2;
        }
        fuelLevelSensor.setValue(arr);

        count += 1 ;
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }
        return fuelLevelSensor;
    }

    /**
     * Значения температур датчиков уровня топлива - 12
     */
    @Override
    public TemperatureFuelLevelSensor setTemperatureFuelLevelSensor(int[] values, int[] baitSteps, long size) {
        TemperatureFuelLevelSensor temperatureFuelLevelSensor = new TemperatureFuelLevelSensor();
        int step = baitSteps[0];
        int count = 0;
        int temp = values[step++];
        int unitCount = 0;

        while (temp != 0) {
            if ((temp & 1) == 1) {
                unitCount++;
            }
            temp = temp >> 1;
        }

        int[] arr = new int[unitCount];
        for (int i = 0; i < unitCount; i++) {
            arr[i] = values[step + i];
            count++;
        }
        temperatureFuelLevelSensor.setValue(arr);

        count += 1;
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }
        return temperatureFuelLevelSensor;
    }

    /**
     * Состояния дискретных входов - 6
     */
    @Override
    public DigitalInputsBk setDigitalInputsBk(int[] values, int[] baitSteps, long size) {
        DigitalInputsBk digitalInputsBk = new DigitalInputsBk();
        int step = baitSteps[0];
        int count = 0;

        digitalInputsBk.setInputBkFlags(Integer.toBinaryString(values[step]));
        count++;

        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }
        return digitalInputsBk;
    }

    /**
     * Состояния АЦП входов БК - 8
     */
    @Override
    public InputsStatusAdc setInputsStatusAdc(int[] values, int[] baitSteps, long size) {
        InputsStatusAdc inputsStatusAdc = new InputsStatusAdc();
        int step = baitSteps[0];
        int count = 0;
        long temp = values[step++];
        int unitCount = 0;

        while (temp != 0) {
            if ((temp & 1) == 1) {
                unitCount++;
            }
            temp = temp >> 1;
        }

        long[] arr = new long[unitCount];
        int j = 0;
        for (int i = 0; i < unitCount; i++) {
            temp = bitOperator.convertBait(values, step + j, 2);
            arr[i] = temp;
            j += 2;
            count += 2;
        }
        inputsStatusAdc.setValue(arr);

        count += 1 ;
        baitSteps[0] += count;
        if (count > size) {
            baitSteps[0] += count - size;
        }
        return inputsStatusAdc;
    }
}
