package com.programming.vnpay.controller;

import com.programming.vnpay.dto.CheckOutRequest;
import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckOutController {

    private final CheckOutService checkOutService;


    @PostMapping
    public String checkOut(@ModelAttribute CheckOutRequest checkOutRequest) {
        Orders order = checkOutService.checkOut(checkOutRequest.getNameCustomer(), checkOutRequest.getAddress());
        System.out.println("hehe");
        return "redirect:/order/" + order.getId();
    }
}