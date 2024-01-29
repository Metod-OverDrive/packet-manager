package com.example.packetmanager.web.mapper.payload.record;

import com.example.packetmanager.domain.payload.record.Telemetry;
import com.example.packetmanager.web.dto.payload.record.TelemetryDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TelemetryMapper extends Mappable<Telemetry, TelemetryDto> {

}
