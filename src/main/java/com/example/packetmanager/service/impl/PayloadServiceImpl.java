package com.example.packetmanager.service.impl;

import com.example.packetmanager.compression.LzCompression;
import com.example.packetmanager.domain.payload.RecordNode;
import com.example.packetmanager.domain.payload.record.RecordContainer;
import com.example.packetmanager.service.PayloadService;
import com.example.packetmanager.service.RecordProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Этот класс обрабатывает раздел Payload с помощью цикла while и switch.
 * Каждую итерацию проходит проверка на индекс телеметрии (0)
 * и если проверка проходит, то RecordContainer (Хранилище записей)
 * добавляется в лист и ссылка переходит на новый объект этого типа.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class PayloadServiceImpl implements PayloadService {

    private final LzCompression compression;
    private final RecordProvider recordProvider;

    @Override
    public List<RecordNode> getRecords(int[] values, boolean isCompressed) {
        List<RecordNode> records = new ArrayList<>();
        RecordContainer recordContainer;
        int[] baitSteps = new int[1];
        RecordNode node = new RecordNode();

        if (isCompressed) {
            int[] out = new int[1500];
            int bounce = compression.uncompress(values, out);
            values = Arrays.stream(out).limit(bounce).toArray();
        }

        while (baitSteps[0] < values.length) {
            recordContainer = recordProvider.nextRecordContainer(values, baitSteps);

            if (recordContainer.getRecordType() == 0) {
                if (baitSteps[0] != 2) {
                    records.add(node);
                    node = new RecordNode();
                }
            }
            switch (recordContainer.getRecordType()) {
                case 0 -> node.setTelemetry(recordProvider.setTelemetry(values,
                        baitSteps, recordContainer.getSize()));

                case 1 -> node.setBasicLocationData(recordProvider.setBasicLocationData(values,
                        baitSteps, recordContainer.getSize()));

                case 2 -> node.setMileage(recordProvider.setMileage(values,
                        baitSteps, recordContainer.getSize()));

                case 3 -> node.setPowerBk(recordProvider.setPowerBk(values,
                        baitSteps, recordContainer.getSize()));

                case 4 -> node.setGsmConnection(recordProvider.setGsmConnection(values,
                        baitSteps, recordContainer.getSize()));

                case 6 -> node.setDigitalInputsBk(recordProvider.setDigitalInputsBk(values,
                        baitSteps, recordContainer.getSize()));

                case 8 -> node.setInputsStatusAdc(recordProvider.setInputsStatusAdc(values,
                        baitSteps, recordContainer.getSize()));

                case 11 -> node.setFuelLevelSensor(recordProvider.setFuelLevelSensor(values,
                        baitSteps, recordContainer.getSize()));

                case 12 -> node.setTemperatureFuelLevelSensor(recordProvider.setTemperatureFuelLevelSensor(values,
                        baitSteps, recordContainer.getSize()));

                default -> baitSteps[0] = values.length + 10;
            }
        }
        return records;
    }
}
