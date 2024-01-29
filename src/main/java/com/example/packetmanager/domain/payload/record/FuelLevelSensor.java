package com.example.packetmanager.domain.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuelLevelSensor {

    private long[] value;

}
