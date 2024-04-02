package com.programming.vnpay.controller;


import com.programming.vnpay.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")

public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/order";
    }
}
