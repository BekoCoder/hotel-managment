package com.example.hotelmanagment.enumeration;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Kutilmoqda"), //kutilmoqda
    CONFIRMED("Tasdiqlandi"),  //tasdiqlandi
    CANCELLED("Bekor qilindi"), //bekor qilindi
    COMPLETED("Tugallandi") //tugallandi
    ;

    OrderStatus(String name) {
        this.name = name;
    }

    private final String name;
}
