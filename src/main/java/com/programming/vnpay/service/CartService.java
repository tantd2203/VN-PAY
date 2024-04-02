package com.programming.vnpay.service;


import com.programming.vnpay.entity.OrderItem;
import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.entity.Product;
import com.programming.vnpay.repository.OrderItemRepository;
import com.programming.vnpay.repository.OrderRepository;
import com.programming.vnpay.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;


    @Transactional
    public void addToCart(Long productId, Integer quantity) {
        // Fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Create a new order item
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice()*quantity);

        // Save the order item
        orderItemRepository.save(orderItem);
    }

    @Transactional
    public void updateCart(Long orderItemId, Integer quantity) {
        // Fetch the order item
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new RuntimeException("Order item not found"));

        // Update the quantity
        orderItem.setQuantity(quantity);

        // Save the order item
        orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteCart(Long orderItemId) {
        orderItemRepository.findById(orderItemId).orElseThrow(() -> new RuntimeException("Order item not found"));
        orderItemRepository.deleteById(orderItemId);
    }

    public List<OrderItem> getCart() {
        // Fetch the current order
        List<OrderItem> orderItem = orderItemRepository.findAll();
        // Return the order items
        return orderItem;
    }
    @Transactional
    public void clearCart() {
        // Delete all the order items
        orderItemRepository.deleteAll();
    }
}
