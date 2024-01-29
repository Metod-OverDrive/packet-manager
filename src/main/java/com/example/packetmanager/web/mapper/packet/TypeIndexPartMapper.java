package com.example.packetmanager.web.mapper.packet;

import com.example.packetmanager.domain.packet.TypeIndexPart;
import com.example.packetmanager.web.dto.packet.TypeIndexPartDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeIndexPartMapper extends Mappable<TypeIndexPart, TypeIndexPartDto> {

}
