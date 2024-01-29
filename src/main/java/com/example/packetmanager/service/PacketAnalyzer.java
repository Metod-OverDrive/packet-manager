package com.example.packetmanager.service;

import com.example.packetmanager.domain.packet.Packet;

public interface PacketAnalyzer {

    Packet buildPacket(String hexPacket);

}
