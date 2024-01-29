package com.example.packetmanager.web.dto.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PacketDto {

    private int signature;
    private long id;
    private String Acknowledge;

}
