package com.programming.vnpay.service;

import com.programming.vnpay.entity.OrderItem;
import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.repository.OrderItemRepository;
import com.programming.vnpay.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckOutService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Orders checkOut(String nameCustomer, String address) {
        // Fetch the cart items
        List<OrderItem> cartItems = cartService.getCart();

        // Calculate the total price
        double total = 0.0;
        for (OrderItem item : cartItems) {
            total += item.getQuantity() * item.getPrice();
        }

        // Create a new order
        Orders order = new Orders();
        order.setNameCustomer(nameCustomer);
        order.setAddress(address);
        order.setAmount_paid(total);
        order.setCreateDate(new Date());

        // Save the order
        order = orderRepository.save(order);

        // Assign the order to the cart items and save them
        for (OrderItem item : cartItems) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        // Clear the cart
        cartService.clearCart();

        return order;
    }
}