package com.example.hotelmanagment.mapper;

import org.mapstruct.Named;

/**
 * created at: 26/10/2024
 * <p>
 *
 * @author: Mirzayev Bekzod
 * <p>
 */

public interface BaseMapper<D, E> {

    @Named(value = "forEntity")
    E toEntity(D dto);


    @Named(value = "forDto")
    D toDto(E entity);

}
