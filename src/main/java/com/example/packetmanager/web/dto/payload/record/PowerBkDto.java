package com.example.packetmanager.web.dto.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PowerBkDto {

    private long boardVoltage;
    private boolean isIgnition;
    private long AkbBkVoltage;
    private boolean lowVoltageSighBk;
    private boolean lowVoltageSighAkb;
    private int energyStatus;
    private boolean evacuationSigh;


}
