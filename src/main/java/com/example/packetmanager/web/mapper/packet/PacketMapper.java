package com.example.packetmanager.web.mapper.packet;

import com.example.packetmanager.domain.packet.Packet;
import com.example.packetmanager.web.dto.packet.PacketDto;
import com.example.packetmanager.web.mapper.Mappable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacketMapper extends Mappable<Packet, PacketDto> {

}
