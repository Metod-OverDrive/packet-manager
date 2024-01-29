package com.example.packetmanager.web.mapper.packet;

import com.example.packetmanager.domain.packet.CrcPart;
import com.example.packetmanager.web.dto.packet.CrcPartDto;
import com.example.packetmanager.web.mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrcPartMapper extends Mappable<CrcPart, CrcPartDto> {

}
