package com.example.hotelmanagment.enumeration;

public enum RoomType {
    LUX("Luks xona"),
    COMFORT("Komfort xona"),
    SIMPLE("Oddiy xona");


    private final String name;

    RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
