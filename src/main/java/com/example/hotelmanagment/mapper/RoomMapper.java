package com.example.hotelmanagment.mapper;

import com.example.hotelmanagment.dto.RoomDto;
import com.example.hotelmanagment.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomMapper extends BaseMapper<RoomDto, Room> {
    @Override
    @Mappings({
            @Mapping(target = "type", source = "roomType")
    })
    @Named(value = "forEntity")
    Room toEntity(RoomDto dto);

    @Override
    @Mappings({
            @Mapping(target = "roomType", source = "type")
    })
    @Named(value = "forDto")
    RoomDto toDto(Room entity);
}
