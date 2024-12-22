package com.example.hotelmanagment.repository;

import com.example.hotelmanagment.dto.RoomDto;
import com.example.hotelmanagment.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.roomNumber=:roomNumber")
    Optional<Room> findByRoomNumber(Integer roomNumber);

    Page<Room> findAllByIsDeleted(Pageable pageable, int isDeleted);
}
