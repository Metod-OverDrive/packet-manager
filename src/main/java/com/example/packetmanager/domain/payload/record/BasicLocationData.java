package com.example.packetmanager.domain.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasicLocationData {

    private long Width;
    private long Longitude;
    private long speed;
    private boolean hemispheresWidth;
    private boolean hemispheresLongitude;
    private long course;
    private int stopSign;
    private int Satellites;
    private long high;
    private boolean highSign;



}
