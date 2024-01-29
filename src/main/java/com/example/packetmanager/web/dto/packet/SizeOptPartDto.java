package com.example.packetmanager.web.dto.packet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SizeOptPartDto {

    private int payloadLength;
    private boolean isId;
    private boolean isTypeIndex;
    private boolean isAcknowledge;
    private boolean isCompilationPayload;
    private boolean isEncryptPayload;

}
