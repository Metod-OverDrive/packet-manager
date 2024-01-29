package com.example.packetmanager.web.mapper.payload.record;

import com.example.packetmanager.domain.payload.record.BasicLocationData;
import com.example.packetmanager.web.dto.payload.record.BasicLocationDataDto;
import com.example.packetmanager.web.mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicLocationDataMapper extends Mappable<BasicLocationData, BasicLocationDataDto> {

}
