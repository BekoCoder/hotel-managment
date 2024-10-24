package com.example.hotelmanagment.repository;

import com.example.hotelmanagment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.roomNumber=:roomNumber")
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
