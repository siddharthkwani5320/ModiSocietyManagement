package com.society.controller;

import com.society.entity.PaymentLink;
import com.society.service.RazorpayService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @GetMapping
    public ResponseEntity<PaymentLink> payment(@RequestParam("amount") int amount){
        try {
            String receiptId = "receipt_" + System.currentTimeMillis();
            String orderId = razorpayService.createOrder(amount, receiptId);
            String paymentUrl = razorpayService.createPaymentLinkWithOrder(orderId, "siddharthkwani532@gmail.com", "9009120289", amount);
            PaymentLink paymentLink=new PaymentLink(paymentUrl);
            return new ResponseEntity<>(paymentLink, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initiating payment: " + e.getMessage());
        }
    }

    @GetMapping("/callback")
    public String callback(@RequestParam Map<String, String> queryParams){
        String paymentStatus = queryParams.get("razorpay_payment_id") != null ? "Success" : "Failure";

        if ("Success".equals(paymentStatus)) {
            return "Payment successful! Your payment ID is: " + queryParams.get("razorpay_payment_id");
        } else {
            return "Payment failed. Please try again.";
        }
    }

}
