package com.example.packetmanager.service.impl;

import com.example.packetmanager.domain.packet.CrcPart;
import com.example.packetmanager.domain.packet.SizeOptPart;
import com.example.packetmanager.domain.packet.TypeIndexPart;
import com.example.packetmanager.service.BitOperator;
import com.example.packetmanager.service.PacketProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Здесь расположены методы для работы с основными полями пакета.
 * В данный момент не реализовано только проверка контрольной суммы CRC.
 */
@Service
@RequiredArgsConstructor
public class PackerProviderImpl implements PacketProvider {

    private final BitOperator bitOperator;

    public SizeOptPart setSizeOptPart(int[] values, int start) {
        SizeOptPart sizeOptPart = new SizeOptPart();
        int bitCode = bitOperator.convertBait(values, start, 2);

        sizeOptPart.setPayloadLength(bitCode & 0b11111111111);
        sizeOptPart.setId(((bitCode >> 11) & 0b1) == 1);
        sizeOptPart.setTypeIndex(((bitCode >> 12) & 0b1) == 1);
        sizeOptPart.setAcknowledge(((bitCode >> 13) & 0b1) == 1);
        sizeOptPart.setCompilationPayload(((bitCode >> 14) & 0b1) == 1);
        sizeOptPart.setEncryptPayload(((bitCode >> 15) & 0b1) == 1);

        return sizeOptPart;
    }

    public long setId(int[] values, int start) {
        return bitOperator.convertBait(values, start, 4);
    }

    public TypeIndexPart setTypeIndexPart(int[] value, int start) {
        TypeIndexPart typeIndexPart = new TypeIndexPart();

        int bitCode = value[start];
        typeIndexPart.setPacketType(bitCode & 0b1111);
        typeIndexPart.setPacketIndex(bitCode & 0b11110000);

        return typeIndexPart;
    }

    public CrcPart setCrcPart(int[] value, int start) {
        CrcPart crcPart = new CrcPart();

        int bitCode = bitOperator.convertBait(value, start, 2);
        crcPart.setControlSum(bitCode);

        int crc = 0xFFFF;
        int[] tempValue = new int[value.length - 3];
        System.arraycopy(value, 1, tempValue, 0, value.length - 3);

        for (int i : tempValue) {
            crc ^= i & 0xFF;

            for (int j = 0; j < 8; j++) {
                if ((crc & 0x0001) != 0) {
                    crc = (crc >> 1) ^ 0x8408;
                } else {
                    crc >>= 1;
                }
            }
        }
        crcPart.setCheckControlSum(crcPart.getControlSum() == crc);
        System.out.println(crc);

        return crcPart;
    }

}
