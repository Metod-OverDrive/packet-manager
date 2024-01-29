package com.example.packetmanager.domain.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrcPart {

    private int controlSum;
    public boolean checkControlSum;

}
