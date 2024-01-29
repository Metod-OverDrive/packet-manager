package com.example.packetmanager.web.mapper.payload;

import com.example.packetmanager.domain.payload.PayloadPart;
import com.example.packetmanager.web.dto.payload.PayloadPartDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface PayloadPartMapper extends Mappable<PayloadPart, PayloadPartDto> {

}
