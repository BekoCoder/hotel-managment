package com.example.hotelmanagment.repository;

import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select d from User d where d.email=:email")
    Optional<User> findByEmail(String email);

    List<User> findByIsDeleted(Integer isDeleted);


}
