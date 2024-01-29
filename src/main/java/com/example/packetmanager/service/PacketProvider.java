package com.example.packetmanager.service;

import com.example.packetmanager.domain.packet.CrcPart;
import com.example.packetmanager.domain.packet.Packet;
import com.example.packetmanager.domain.packet.SizeOptPart;
import com.example.packetmanager.domain.packet.TypeIndexPart;

public interface PacketProvider {

    SizeOptPart setSizeOptPart(int[] values, int start);
    long setId(int[] values, int start);
    TypeIndexPart setTypeIndexPart(int[] value, int start);
    CrcPart setCrcPart(int[] value, int start);
}
