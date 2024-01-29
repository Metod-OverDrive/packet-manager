package com.example.packetmanager.web.mapper.payload.record;

import com.example.packetmanager.domain.payload.record.TemperatureFuelLevelSensor;
import com.example.packetmanager.web.dto.payload.record.TemperatureFuelLevelSensorDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemperatureFuelLevelSensorMapper extends Mappable<TemperatureFuelLevelSensor,
        TemperatureFuelLevelSensorDto> {

}
