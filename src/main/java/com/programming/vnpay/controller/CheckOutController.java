package com.programming.vnpay.controller;

import com.programming.vnpay.dto.CheckOutRequest;
import com.programming.vnpay.entity.Orders;
import com.programming.vnpay.service.CheckOutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckOutController {

    private final CheckOutService checkOutService;

    @PostMapping
    public String checkOut(@ModelAttribute CheckOutRequest checkOutRequest,
                           HttpServletRequest request) {

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = checkOutService.checkOutWithPayOnline(checkOutRequest.getNameCustomer(), checkOutRequest.getAddress(), baseUrl);
        System.out.println("vnpayUrl: " + vnpayUrl);
        return "redirect:" + vnpayUrl;

    }
    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model){
        int paymentStatus =checkOutService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}