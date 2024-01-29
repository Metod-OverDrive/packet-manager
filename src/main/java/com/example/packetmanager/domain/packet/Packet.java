package com.example.packetmanager.domain.packet;

import com.example.packetmanager.domain.payload.RecordNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Packet {

    private int signature;
    private SizeOptPart sizeOptPart;
    private long id;
    private TypeIndexPart typeIndexPart;
    private String Acknowledge;
    private List<RecordNode> payloadPart;
    private CrcPart crcPart;

}
