package com.example.packetmanager.web.mapper.packet;

import com.example.packetmanager.domain.packet.SizeOptPart;
import com.example.packetmanager.web.dto.packet.SizeOptPartDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeOptPartMapper extends Mappable<SizeOptPart, SizeOptPartDto> {

}
