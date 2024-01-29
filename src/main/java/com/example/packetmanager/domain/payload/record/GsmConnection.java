package com.example.packetmanager.domain.payload.record;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GsmConnection {

    private int GsmLevelConnection;
    private boolean connectionSigh;
    private boolean packetConnectionSigh;
    private boolean connectionSighWithCc;
    private long operatorCode;
    private boolean roamingRegistrationSigh;
    private boolean numberActiveSim;
    private boolean additionalConnectionWithCc;
    private boolean numberActivAntennaGsm;
    private int lacGsm;
    private long cellIdGsm;

}
