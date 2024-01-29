package com.example.packetmanager.web.dto.payload.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicLocationDataDto {

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
