package com.example.hotelmanagment.enumeration;

import lombok.Getter;

@Getter
public enum RoomType {
    LUX("Luks xona"),
    COMFORT("Komfort xona"),
    STANDART("Oddiy xona");


    private final String name;

    RoomType(String name) {
        this.name = name;
    }

}
