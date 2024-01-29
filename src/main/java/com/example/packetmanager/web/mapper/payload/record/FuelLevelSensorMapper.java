package com.example.packetmanager.web.mapper.payload.record;

import com.example.packetmanager.domain.payload.record.FuelLevelSensor;
import com.example.packetmanager.web.dto.payload.record.FuelLevelSensorDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelLevelSensorMapper extends Mappable<FuelLevelSensor, FuelLevelSensorDto> {

}
