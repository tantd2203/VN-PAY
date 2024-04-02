package com.programming.vnpay.controller;

import com.programming.vnpay.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping
    public String getCart(Model model) {
      model.addAttribute("cart", cartService.getCart());
        return "cart/cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam Integer quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{orderItemId}")
    public String updateCart(@PathVariable Long orderItemId, @RequestParam Integer quantity) {
        cartService.updateCart(orderItemId, quantity);
        return "redirect:/cart";
    }
    @GetMapping("/delete/{orderItemId}")
    public String deleteCart(@PathVariable Long orderItemId) {
        cartService.deleteCart(orderItemId);
        return "redirect:/cart";
    }
}
