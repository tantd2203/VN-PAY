package com.programming.vnpay.service;


import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
