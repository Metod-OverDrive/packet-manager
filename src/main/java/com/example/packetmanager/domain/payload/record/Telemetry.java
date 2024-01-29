package com.example.packetmanager.domain.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Telemetry {

    private LocalDateTime messageTime;
    private int messageIndex;
    private int messageCode;

}
