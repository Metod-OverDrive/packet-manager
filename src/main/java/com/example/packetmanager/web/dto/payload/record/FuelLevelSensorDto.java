package com.example.packetmanager.web.dto.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuelLevelSensorDto {

    private long[] value;

}
