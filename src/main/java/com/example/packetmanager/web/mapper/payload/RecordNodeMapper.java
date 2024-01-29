package com.example.packetmanager.web.mapper.payload;

import com.example.packetmanager.domain.payload.RecordNode;
import com.example.packetmanager.web.dto.payload.RecordNodeDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordNodeMapper extends Mappable<RecordNode, RecordNodeDto> {

}
