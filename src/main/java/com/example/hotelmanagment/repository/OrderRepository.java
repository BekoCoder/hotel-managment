package com.example.hotelmanagment.repository;

import com.example.hotelmanagment.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByIsDeleted(Integer isDeleted, Pageable pageable);
}
