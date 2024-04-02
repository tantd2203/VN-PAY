package com.programming.vnpay.service;


import com.programming.vnpay.entity.Cart;
import com.programming.vnpay.entity.OrderItem;
import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.entity.Product;
import com.programming.vnpay.repository.CartRepository;
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
    private final CartRepository cartRepository;


    @Transactional
    public void addToCart(Long productId, Integer quantity) {
        // Fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Create a new order item
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setPrice(product.getPrice() * quantity);

        // Save the order item
        cartRepository.save(cart);
    }

    @Transactional
    public void updateCart(Long orderItemId, Integer quantity) {
        // Fetch the order item
        Cart cart = cartRepository.findById(orderItemId).orElseThrow(() -> new RuntimeException("Order item not found"));

        // Update the quantity
        cart.setQuantity(quantity);

        // Save the order item
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Order item not found"));
        cartRepository.deleteById(cartId);
    }

    public List<Cart> getCart() {
        // Fetch the current order
        List<Cart> cartList = cartRepository.findAll();
        // Return the order items
        return cartList;
    }

    @Transactional
    public void clearCart() {
        // Delete all the order items
        cartRepository.deleteAll();
    }
}
