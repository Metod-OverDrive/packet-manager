package com.example.packetmanager.domain.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SizeOptPart {

    private int payloadLength;
    private boolean isId;
    private boolean isTypeIndex;
    private boolean isAcknowledge;
    private boolean isCompilationPayload;
    private boolean isEncryptPayload;

}
