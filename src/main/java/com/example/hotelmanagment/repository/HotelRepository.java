package com.example.hotelmanagment.repository;

import com.example.hotelmanagment.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h from Hotel h where h.name=:name")
    Optional<Hotel> findByName(String name);

    Page<Hotel> findAllByIsDeleted(Integer isDeleted, Pageable pageable);
}
