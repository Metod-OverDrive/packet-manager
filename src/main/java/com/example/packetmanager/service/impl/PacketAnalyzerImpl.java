package com.example.packetmanager.service.impl;

import com.example.packetmanager.domain.packet.Packet;
import com.example.packetmanager.service.BitOperator;
import com.example.packetmanager.service.PacketAnalyzer;
import com.example.packetmanager.service.PacketProvider;
import com.example.packetmanager.service.PayloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Это основной класс, в котором HEX строка преобразуется в читаемый пакет.
 * Здесь происходит:
 *  перевод HEX String в Dec int[];
 *  Получение основных полей пакета;
 *  Получение Payload;
 */


@Service
@RequiredArgsConstructor
public class PacketAnalyzerImpl implements PacketAnalyzer {

    private final PacketProvider packetProvider;
    private final PayloadService payloadService;
    private final BitOperator bitOperator;

    @Override
    public Packet buildPacket(String hexPacket) {
        Packet packet = new Packet();
        int[] values = bitOperator.hexStringToIntArray(hexPacket);

        packet.setSignature(values[0]);
        packet.setSizeOptPart(packetProvider.setSizeOptPart(values, 1));
        packet.setId(packetProvider.setId(values, 3));
        packet.setTypeIndexPart(packetProvider.setTypeIndexPart(values, 7));
        packet.setCrcPart(packetProvider.setCrcPart(values, values.length - 2));

        int[] tempPacket = new int[values.length - 10];
        System.arraycopy(values, 8, tempPacket, 0, values.length - 10);
        packet.setPayloadPart(payloadService.getRecords(tempPacket, packet.getSizeOptPart().isCompilationPayload()));

        return packet;
    }

}
