package com.example.hotelmanagment.mapper;

import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends BaseMapper<UserDto, User> {
    @Override
    @Mappings({
            @Mapping(target = "roles", source = "role")
    })
    @Named(value = "forEntity")
    User toEntity(UserDto dto);

    @Override
    @Mappings({
            @Mapping(target = "role", source = "roles")
    })
    @Named(value = "forDto")
    UserDto toDto(User entity);
}
