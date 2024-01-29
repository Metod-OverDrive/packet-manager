package com.example.packetmanager.web.mapper;

public interface Mappable<E, D>{

    E toEntity(D dto);

    D toDto(E entity);
}
