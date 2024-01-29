package com.example.packetmanager.web.dto.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrcPartDto {

    private int controlSum;
    public boolean checkControlSum;

}
