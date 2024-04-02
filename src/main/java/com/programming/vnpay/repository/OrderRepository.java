package com.programming.vnpay.repository;


import com.programming.vnpay.entity.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM orders o ORDER BY o.createDate DESC")
    Optional<Orders> findCurrentOrder();

}
