package com.example.hotelmanagment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel extends AbstractEntity {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String city;

    @OneToMany(mappedBy = "hotel")
    private List<Room> room;

}
